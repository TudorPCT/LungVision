class PredictionDto:
    def __init__(self, prediction, probability):
        self.prediction = prediction
        self.probability = probability

    def __str__(self):
        return "{\"prediction\": \"" + self.prediction + "\", " \
                "\"probability\": \"" + str(self.probability) + "\"}"
