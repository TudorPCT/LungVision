import tensorflow as tf
import os

from models.util import train_model, evaluate_model


def build_model(data_shape, num_classes):
    base_model = tf.keras.applications.vgg16.VGG16(
        include_top=False,
        weights='imagenet',
        classifier_activation='softmax',
        input_shape=data_shape
    )
    base_model.trainable = False

    model = tf.keras.models.Sequential([
        base_model,
        tf.keras.layers.Flatten(),
        tf.keras.layers.Dense(64),
        tf.keras.layers.Dense(32),
        tf.keras.layers.Dense(num_classes, activation="softmax")
    ])
    return model


if __name__ == '__main__':

    data_shape_vgg16 = (224, 224, 3)
    num_classes_vgg16 = 4
    path = '../../LungDiseaseDataset'

    model = build_model(data_shape_vgg16, num_classes_vgg16)

    model.summary()

    train_dataset = tf.keras.preprocessing.image_dataset_from_directory(
            f"{path}/train",
            batch_size=64,
            image_size=(224, 224),
            preprocess_function=tf.keras.applications.vgg16.preprocess_input
        )
    validation_dataset = tf.keras.preprocessing.image_dataset_from_directory(
            f"{path}/val",
            batch_size=64,
            image_size=(224, 224),
            preprocess_function=tf.keras.applications.vgg16.preprocess_input
        )
    test_dataset = tf.keras.preprocessing.image_dataset_from_directory(
            f"{path}/test",
            batch_size=64,
            image_size=(224, 224),
            preprocess_function=tf.keras.applications.vgg16.preprocess_input
        )

    filename = os.path.splitext(os.path.basename(__file__))[0]

    train_model(model, filename, train_dataset, validation_dataset, 10)

    evaluate_model(model, test_dataset)
