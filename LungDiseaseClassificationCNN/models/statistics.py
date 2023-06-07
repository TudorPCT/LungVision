import os

import matplotlib.pyplot as plt
import numpy as np
from tensorflow import keras


def get_loss_accuracy_all_epochs(model_path, dataset):
    loss = list()
    accuracy = list()

    epochs = os.listdir(model_path)

    for epoch in epochs:
        model = keras.models.load_model(model_path + "/" + epoch)
        model_loss, model_accuracy = model.evaluate(dataset)
        loss.append(model_loss)
        accuracy.append(model_accuracy)

    return loss, accuracy


def draw_loss_accuracy_graph(model_path, test_dataset):


    filename = os.path.basename(model_path)
    history = np.load(f'../model_history/{filename}-history.npy', allow_pickle=True).item()

    test_loss, test_accuracy = get_loss_accuracy_all_epochs(model_path, test_dataset)

    epochs_count = range(1, len(test_loss) + 1)

    plt.plot(epochs_count, test_loss, 'r', label='Eroare testare')
    plt.plot(epochs_count, history['loss'], 'g', label='Eroare antrenare')
    plt.plot(epochs_count, history['val_loss'], 'b', label='Eroare validare')
    plt.title('Eroare')
    plt.xlabel('Epoca')
    plt.ylabel('Eroare')
    plt.legend()
    plt.savefig(f'../model_history/{filename}-loss.png')
    plt.clf()

    plt.plot(epochs_count, test_accuracy, 'r', label='Acuratete testare')
    plt.plot(epochs_count, history['accuracy'], 'g', label='Acuratete antrenare')
    plt.plot(epochs_count, history['val_accuracy'], 'b', label='Acuratete validare')
    plt.title('Acuratete')
    plt.xlabel('Epoca')
    plt.ylabel('Acuratete')
    plt.legend()
    plt.savefig(f'../model_history/{filename}-accuracy.png')
    plt.clf()
