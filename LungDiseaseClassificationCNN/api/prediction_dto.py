class PredictionDto:
    def __init__(self, prediction, probability, labels):
        self.prediction = prediction
        self.probability = probability
        self.labels = labels

    def __str__(self):
        return "{\"prediction\": \"" + self.prediction + "\", " \
                "\"probability\": \"" + str(self.probability) + "\"}"
