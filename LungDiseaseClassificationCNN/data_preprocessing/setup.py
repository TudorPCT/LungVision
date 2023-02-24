import os
from glob import glob
import albumentations as A
from albumentations.pytorch import ToTensorV2


def get_train_transform():
    return A.Compose(
        [
            A.ShiftScaleRotate(shift_limit=0.05, scale_limit=0.05, rotate_limit=15, p=0.5),
            A.HorizontalFlip(p=0.5),
            A.RandomBrightnessContrast(p=0.5),
            A.ColorJitter(p=0.5),
            A.GaussNoise(mean=10, p=0.5),
            A.Normalize(mean=(0.485, 0.456, 0.406), std=(0.229, 0.224, 0.225)),
            ToTensorV2(),
        ]
    )


def get_original_transform():
    return A.Compose(
        [
            A.Normalize(mean=(0.485, 0.456, 0.406), std=(0.229, 0.224, 0.225)),
            ToTensorV2(),
        ]
    )


def get_datasets_paths():
    datasets = {
        'train': [],
        'val': [],
        'test': []
    }

    for phase in ['train', 'val', 'test']:
        image = []
        for i in glob(f'./output/{phase}/**/*'):
            if i.split('.')[-1] not in ['jpg', 'jpeg', 'png'] or i.split('/')[-1].startswith('.'):
                continue
            image.append(i)
        datasets[phase] = image

    return datasets


def create_labels_folders(path, phase):
    try:
        os.mkdir(f'{path}/{phase}')
        os.mkdir(f'{path}/{phase}/Normal')
        os.mkdir(f'{path}/{phase}/Pneumonia')
        os.mkdir(f'{path}/{phase}/Covid19')
        os.mkdir(f'{path}/{phase}/Tuberculosis')
    except IsADirectoryError as e:
        print(e)


def create_folders(path):
    try:
        os.mkdir(path)
        create_labels_folders(path, "train")
        create_labels_folders(path, "val")
        create_labels_folders(path, "test")
    except IsADirectoryError as e:
        print(e)
