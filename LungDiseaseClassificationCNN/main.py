import os
import shutil

import splitfolders

# https://www.medrxiv.org/content/10.1101/2021.07.30.21261225v1.full
#
# os.mkdir("LungDiseaseOriginalDataset")
# os.mkdir("LungDiseaseOriginalDataset/Normal")
# os.mkdir("LungDiseaseOriginalDataset/Pneumonia")
# os.mkdir("LungDiseaseOriginalDataset/Tuberculosis")
# os.mkdir("LungDiseaseOriginalDataset/Covid19")
#
#
# for phase in os.listdir("./archive-2"):
#     if phase.startswith("."):
#         continue
#     for label in os.listdir(f"./archive-2/{phase}"):
#         if label.startswith("."):
#             continue
#         for image in os.listdir(f"./archive-2/{phase}/{label}"):
#             if image.startswith("."):
#                 continue
#             shutil.move(f"./archive-2/{phase}/{label}/{image}", f"LungDiseaseOriginalDataset/{label}/{image}")
#
#
splitfolders.ratio('./LungDiseaseOriginalDataset', ratio=(.8, .1, .1))
#
# for label in os.listdir(f"./LungDiseaseOriginalDataset"):
#     for image in os.listdir(f"./LungDiseaseOriginalDataset/{label}"):
#         if image.startswith("."):
#             os.remove(f"./LungDiseaseOriginalDataset/{label}/{image}")