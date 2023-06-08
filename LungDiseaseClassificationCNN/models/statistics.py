import os

import matplotlib.pyplot as plt
import numpy as np
from tensorflow import keras, math
import seaborn as sns

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

    plt.plot(epochs_count, test_loss, 'r', label='Testare')
    plt.plot(epochs_count, history['loss'], 'g', label='Antrenare')
    plt.plot(epochs_count, history['val_loss'], 'b', label='Validare')
    plt.xlabel('Epoca')
    plt.ylabel('Eroare')
    plt.legend()
    plt.savefig(f'../model_graphs/{filename}-loss.png')
    plt.clf()

    plt.plot(epochs_count, test_accuracy, 'r', label='Testare')
    plt.plot(epochs_count, history['accuracy'], 'g', label='Antrenare')
    plt.plot(epochs_count, history['val_accuracy'], 'b', label='Validare')
    plt.xlabel('Epoca')
    plt.ylabel('Acuratețe')
    plt.legend()
    plt.savefig(f'../model_graphs/{filename}-accuracy.png')
    plt.clf()

    return test_accuracy.index(max(test_accuracy)) + 1


def draw_confusion_matrix(model_path, dataset):
    predicted = list()
    actual = list()

    model = keras.models.load_model(model_path)

    for batch, labels in dataset:
        actual += list(labels)
        predicted += list(np.argmax(model.predict(batch), axis=-1))

    confusion_matrix = math.confusion_matrix(actual, predicted)

    plt.figure(figsize=(10, 7))
    sns.set()
    sns.heatmap(confusion_matrix,
                annot=True,
                xticklabels=dataset.class_names,
                yticklabels=dataset.class_names,
                cmap=sns.dark_palette("#69d", as_cmap=True))

    plt.xlabel('Estimat')
    plt.ylabel('Corect')

    folder_names = model_path.split('/')
    plt.savefig(f'../model_graphs/{folder_names[-2]}-{folder_names[-1]}-confusion_matrix.png')





