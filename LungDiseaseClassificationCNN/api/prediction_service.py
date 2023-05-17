
import numpy as np
import tensorflow as tf
from tensorflow.python.keras.models import Model
from api.prediction_dto import PredictionDto


class PredictionService:
    model: Model = tf.keras.models.load_model("./LungDiseaseClassificationCNN/models/trained_model/model-3/epoch_06")
    shape = model.get_config()["layers"][0]['config']["batch_input_shape"][1:4]
    labels = ['Covid19', 'Normal', 'Pneumonia', 'Tuberculosis']

    @staticmethod
    def predict(scan_bytes):

        scan = tf.io.decode_image(scan_bytes, channels=PredictionService.shape[2])

        scan = tf.image.resize_with_pad(scan, PredictionService.shape[0], PredictionService.shape[1]).numpy()

        scan_prediction = PredictionService.model.predict(np.expand_dims(scan, axis=0))

        prediction_dto = PredictionDto(PredictionService.labels[np.argmax(scan_prediction[0])],
                                       scan_prediction[0], PredictionService.labels)

        return prediction_dto
