import tensorflow as tf
import numpy as np


def load_data(path, batch_size, image_size, color_mode):
    return tf.keras.preprocessing.image_dataset_from_directory(
        path,
        batch_size=batch_size,
        image_size=image_size,
        color_mode=color_mode
    )


def load_datasets(path, batch_size, image_size, color_mode='rgb'):
    train_dataset = load_data(f"{path}/train", batch_size, image_size, color_mode)
    validation_dataset = load_data(f"{path}/val", batch_size, image_size, color_mode)
    test_dataset = load_data(f"{path}/test", batch_size, image_size, color_mode)
    return train_dataset, validation_dataset, test_dataset


def train_model(model, filename, train_dataset, val_dataset, epoch_count):

    callbacks = [
        tf.keras.callbacks.ModelCheckpoint(
            filepath=f'../trained_model/{filename}/epoch_' + '{epoch:02d}',
            save_freq='epoch')
    ]

    model.compile(
        optimizer='adam',
        loss='sparse_categorical_crossentropy',
        metrics=["accuracy"]
    )

    history = model.fit(
        train_dataset,
        epochs=epoch_count,
        callbacks=callbacks,
        shuffle=True,
        validation_data=val_dataset
    )

    print(history.history)

    np.save(f'../model_history/{filename}-history.npy', history.history)


def evaluate_model(model, dataset):
    loss, accuracy = model.evaluate(dataset)
    print("loss: %.2f" % loss)
    print("acc: %.2f" % accuracy)
