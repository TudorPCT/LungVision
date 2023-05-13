import io
import os

import PIL.Image as Image
import numpy as np
import tensorflow as tf
from tensorflow.python.keras.models import Model
from api.prediction_dto import PredictionDto
from data_preprocessing.setup import get_original_transform


class PredictionService:
    model: Model = tf.keras.models.load_model("../models/trained_model/model-3/epoch_06")
    shape = model.get_config()["layers"][0]['config']["batch_input_shape"][1:4]
    labels = ['Covid19', 'Normal', 'Pneumonia', 'Tuberculosis']

    @staticmethod
    def predict(scan_bytes):

        scan = tf.io.decode_image(scan_bytes, channels=PredictionService.shape[2]).numpy()

        for transform in get_original_transform():
            scan = transform(image=scan)["image"]

        scan_prediction = PredictionService.model.predict(np.expand_dims(scan, axis=0))

        prediction_dto = PredictionDto(PredictionService.labels[np.argmax(scan_prediction[0])],
                                       scan_prediction)

        return prediction_dto
