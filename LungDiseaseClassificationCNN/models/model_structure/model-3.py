import tensorflow as tf
import os

from models.util import train_model, load_datasets, evaluate_model


def build_model(data_shape, num_classes):
    inputs = tf.keras.Input(shape=data_shape)
    x = tf.keras.layers.Rescaling(scale=1.0 / 255)(inputs)
    x = tf.keras.layers.Conv2D(filters=64, kernel_size=(3, 3), activation="relu")(x)
    x = tf.keras.layers.MaxPooling2D(pool_size=(3, 3))(x)
    x = tf.keras.layers.Conv2D(filters=128, kernel_size=(3, 3), activation="relu")(x)
    x = tf.keras.layers.MaxPooling2D(pool_size=(3, 3))(x)
    x = tf.keras.layers.Conv2D(filters=256, kernel_size=(3, 3), activation="relu")(x)
    x = tf.keras.layers.MaxPooling2D(pool_size=(3, 3))(x)
    x = tf.keras.layers.Dense(64)(x)
    x = tf.keras.layers.Flatten()(x)

    outputs = tf.keras.layers.Dense(num_classes, activation="softmax")(x)

    model = tf.keras.Model(inputs=inputs, outputs=outputs)

    return model


if __name__ == '__main__':
    data_shape_4 = (256, 256, 3)
    num_classes_4 = 4
    path = "../../LungDiseaseDataset"

    model_4 = build_model(data_shape_4, num_classes_4)
    model_4.summary()

    train_dataset, validation_dataset, test_dataset = load_datasets(path, 64, data_shape_4[0:2])

    filename = os.path.splitext(os.path.basename(__file__))[0]

    train_model(model_4, filename, train_dataset, validation_dataset, 10)

    evaluate_model(model_4, test_dataset)


# Epoch 1: 0.9340
# Epoch 2: 0.9452
# Epoch 3: 0.9466
# Epoch 4: 0.9424
# Epoch 5: 0.9466
# Epoch 6: 0.9607
# Epoch 7: 0.9537
# Epoch 8: 0.9593
# Epoch 9: 0.9522
# Epoch 10: 0.9551
# Test epoch 10: 0.9581
