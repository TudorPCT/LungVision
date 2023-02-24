import os
import cv2
from torch.utils.data import Dataset


class LungDiseaseDataset(Dataset):
    def __init__(self, image_paths, transform=None):
        self.image_paths = image_paths
        self.transform = transform
        self.labels_dict = {'Normal': 0, 'Pneumonia': 1, 'Tuberculosis': 2, 'Covid19': 3,
                            0: 'Normal', 1: 'Pneumonia', 2: 'Tuberculosis', 3: 'Covid19'}

    def __len__(self):
        return len(self.image_paths)

    def __getitem__(self, index):
        image_filepath = self.image_paths[index]
        image = cv2.imread(image_filepath)
        image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
        label = self.labels_dict[os.path.normpath(image_filepath).split(os.sep)[-2]]
        if self.transform is not None:
            image = self.transform(image=image)["image"]

        return image, label
