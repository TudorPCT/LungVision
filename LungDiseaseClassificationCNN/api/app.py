from flask import Flask
from flask_cors import CORS

from api.prediction_controller import PredictionController

app = Flask(__name__)
CORS(app)

prediction_controller = PredictionController(app)

if __name__ == '__main__':
    app.run(debug=False)
