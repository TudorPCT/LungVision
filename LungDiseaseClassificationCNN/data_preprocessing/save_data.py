import cv2
from albumentations.pytorch import ToTensorV2
import albumentations as A
import uuid


def original_save(path, phase, original_dataset, index):
    image, label = original_dataset[index]
    cv2.imwrite(f'{path}/{phase}/{original_dataset.labels_dict[label]}/{str(uuid.uuid4())}.jpg', image)


def alb_save(path, phase, alb_dataset, index, lens):
    image, label = alb_dataset[index]
    for _ in range(lens[label]):
        cv2.imwrite(f'{path}/{phase}/{alb_dataset.labels_dict[label]}/{str(uuid.uuid4())}.jpg', image)
        image, label = alb_dataset[index]


def save_data(path, phase, dataset, limit, data_type='original'):
    dataset.transform = A.Compose(
        [instance_transform for instance_transform in dataset.transform
         if not isinstance(instance_transform, (A.Normalize, ToTensorV2))]
    )
    lens = {0: 4000 // 1583, 1: 4273 // 4273, 2: 4000 // 700, 3: 4000 // 576}
    for index in range(limit):

        if data_type == 'original':
            original_save(path, phase, dataset, index)
        elif data_type == 'alb':
            alb_save(path, phase, dataset, index, lens)
