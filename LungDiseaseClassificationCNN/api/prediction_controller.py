import pdb

from flask import request, Response

from api.prediction_service import PredictionService


class PredictionController:
    def __init__(self, app):
        self.app = app
        self.register_routes()

    def register_routes(self):
        @self.app.route("/")
        def hello():
            return "This is the Lung Disease Prediction API."

        @self.app.route("/help")
        def api_help():
            return f"{{\"Input\": \"scan: bytes\", \n" \
                   f"\"Output\": \"prediction, probability\"\n" \
                   f"\"Labels\": {PredictionService.labels}}}"

        @self.app.route("/predict", methods=["POST"])
        def predict():

            try:
                scan_dto = request.data
            except KeyError:
                return Response("{\"message\": \"Invalid input\"}", status=400, mimetype='application/json')

            try:
                prediction = PredictionService.predict(scan_dto)
            except Exception as e:
                return Response("{\"message\": \"" + e.__str__() + "\"}", status=500, mimetype='application/json')

            if prediction is not None:
                return Response(prediction.__str__(), status=200, mimetype='application/json')
            else:
                return Response("{\"message\": \"Prediction failed\"}", status=500, mimetype='application/json')
