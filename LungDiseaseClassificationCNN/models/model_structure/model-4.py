import tensorflow as tf
import os

from models.statistics import draw_loss_accuracy_graph
from models.util import train_model, load_datasets, evaluate_model


def build_model(shape, class_count):
    inputs = tf.keras.Input(shape=shape)
    x = tf.keras.layers.Rescaling(scale=1.0 / 255)(inputs)
    x = tf.keras.layers.Conv2D(filters=32, kernel_size=(3, 3), activation="relu")(x)
    x = tf.keras.layers.MaxPooling2D(pool_size=(2, 2))(x)
    x = tf.keras.layers.Conv2D(filters=64, kernel_size=(3, 3), activation="relu")(x)
    x = tf.keras.layers.MaxPooling2D(pool_size=(2, 2))(x)
    x = tf.keras.layers.Flatten()(x)
    x = tf.keras.layers.Dense(1024, activation="relu")(x)
    x = tf.keras.layers.Dense(200, activation="relu")(x)

    outputs = tf.keras.layers.Dense(class_count, activation="softmax")(x)

    return tf.keras.Model(inputs=inputs, outputs=outputs)


if __name__ == '__main__':
    data_shape = (32, 32, 1)
    num_classes = 4
    path = "../../LungDiseaseDataset"

    model = build_model(data_shape, num_classes)
    model.summary()

    train_dataset, validation_dataset, test_dataset = load_datasets(path, 64, data_shape[0:2], color_mode="grayscale")

    filename = os.path.splitext(os.path.basename(__file__))[0]

    train_model(model, filename, train_dataset, validation_dataset, 10)

    evaluate_model(model, test_dataset)

    draw_loss_accuracy_graph("../trained_model/model-4", test_dataset)
