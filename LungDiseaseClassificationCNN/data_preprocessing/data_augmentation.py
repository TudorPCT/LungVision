import warnings
from data_preprocessing.lung_disease_dataset import LungDiseaseDataset
from data_preprocessing.save_data import save_data
from data_preprocessing.setup import get_datasets_paths, get_train_transform, get_original_transform, create_folders


def data_augmentation(original_dataset_path, output_path):
    warnings.simplefilter('ignore')

    # splitfolders.ratio(original_dataset_path, ratio=(.8, .1, .1))

    datasets = get_datasets_paths()

    train_transform = get_train_transform()

    original_transform = get_original_transform()

    alb_dataset = LungDiseaseDataset(image_paths=datasets['train'], transform=train_transform)
    original_train_dataset = LungDiseaseDataset(image_paths=datasets['train'], transform=original_transform)
    validation_dataset = LungDiseaseDataset(image_paths=datasets['val'], transform=original_transform)
    test_dataset = LungDiseaseDataset(image_paths=datasets['test'], transform=original_transform)

    create_folders(output_path)

    dataset_sizes = {phase: len(datasets[phase]) for phase in ['train', 'val', 'test']}

    save_data(output_path, 'train', original_train_dataset, dataset_sizes['train'])
    save_data(output_path, 'train', alb_dataset, dataset_sizes['train'], data_type='alb')
    save_data(output_path, 'val', validation_dataset, dataset_sizes['val'])
    save_data(output_path, 'test', test_dataset, dataset_sizes['test'])


if __name__ == '__main__':
    data_augmentation('../LungDiseaseOriginalDataset', '../LungDiseaseDataset')
