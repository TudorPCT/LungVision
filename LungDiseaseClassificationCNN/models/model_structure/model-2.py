import tensorflow as tf
import os

from models.util import train_model, load_datasets, evaluate_model


def build_model(shape, class_count):
    inputs = tf.keras.Input(shape=shape)
    x = tf.keras.layers.Rescaling(scale=1.0 / 255)(inputs)
    x = tf.keras.layers.Conv2D(filters=64, kernel_size=(3, 3), activation="relu")(x)
    x = tf.keras.layers.MaxPooling2D(pool_size=(3, 3))(x)
    x = tf.keras.layers.Dense(64)(x)
    x = tf.keras.layers.Conv2D(filters=128, kernel_size=(3, 3), activation="relu")(x)
    x = tf.keras.layers.MaxPooling2D(pool_size=(3, 3))(x)
    x = tf.keras.layers.Conv2D(filters=256, kernel_size=(3, 3), activation="relu")(x)
    x = tf.keras.layers.MaxPooling2D(pool_size=(3, 3))(x)
    x = tf.keras.layers.Flatten()(x)

    outputs = tf.keras.layers.Dense(class_count, activation="softmax")(x)

    return tf.keras.Model(inputs=inputs, outputs=outputs)


if __name__ == '__main__':
    data_shape = (256, 256, 3)
    num_classes = 4
    path = "../../output"

    model = build_model(data_shape, num_classes)
    model.summary()

    train_dataset, validation_dataset, test_dataset = load_datasets(path, 64, data_shape[0:2])

    filename = os.path.splitext(os.path.basename(__file__))[0]

    train_model(model, filename, train_dataset, validation_dataset, 10)

    # model = tf.keras.models.load_model("../trained_model/model-3/epoch_06")

    evaluate_model(model, test_dataset)
