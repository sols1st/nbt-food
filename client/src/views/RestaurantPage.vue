<style scoped>
.example-content {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
}
</style>

<template>
    <ion-page mode="ios">
        <ion-header>
            <ion-toolbar>
                <ion-title>图片上传</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content>
            <div>
                <input type="file" ref="fileInput" @change="handleFileChange" accept="image/*" />
                <button @click="uploadImage">Upload</button>
                <div v-if="imageUrl">
                    <img :src="imageUrl" alt="Uploaded image" />
                </div>
            </div>

        </ion-content>
    </ion-page>
</template>


<script setup lang="ts">
import { IonHeader, IonToolbar, IonTitle, IonContent, IonPage } from '@ionic/vue';
import { ref } from 'vue';
import axios from 'axios';

const fileInput = ref<HTMLInputElement | null>(null);
const selectedFile = ref<File | null>(null);
const imageUrl = ref<string | null>(null);

const handleFileChange = (): void => {
    if (fileInput.value && fileInput.value.files) {
        selectedFile.value = fileInput.value.files[0];
    }
};

const uploadImage = async (): Promise<void> => {
    if (selectedFile.value) {
        const formData = new FormData();
        formData.append('file', selectedFile.value);

        try {
            const response = await axios.post('http://127.0.0.1:8080/api/upload', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });
            alert('Image uploaded successfully.');
            imageUrl.value = "http://127.0.0.1:8080/api" + response.data.data;
        } catch (error) {
            console.error('Failed to upload image:', error);
            alert('Failed to upload image.');
        }
    } else {
        alert('Please select an image file.');
    }
};
</script>

