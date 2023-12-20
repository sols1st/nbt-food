<template>
    <ion-page mode="ios">
        <ion-header>
            <ion-toolbar>
                <ion-title>个人中心</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content>
            <div class="flex justify-center">
                <ion-button id="open-modal" expand="block" fill="outline" style="width: calc(100% - 28px);">注册</ion-button>
            </div>
            <ion-button id="present-alert">登录</ion-button>
            <ion-alert trigger="present-alert" header="登录" sub-header="请注意输入正确"  :buttons="alertButtons"
                :inputs="alertInputs"></ion-alert>
            <ion-modal mode="ios" ref="modal" trigger="open-modal" @willDismiss="onWillDismiss">
                <ion-header>
                    <ion-toolbar>
                        <ion-buttons slot="start">
                            <ion-button @click="cancel()">取消</ion-button>
                        </ion-buttons>
                        <ion-title>注册</ion-title>
                        <ion-buttons slot="end">
                            <ion-button @click="confirm()">确认</ion-button>
                        </ion-buttons>
                    </ion-toolbar>
                </ion-header>
                <ion-content color="light">
                    <ion-list :inset="true">
                        <ion-item>
                            <ion-input label="昵称"></ion-input>
                        </ion-item>
                        <ion-item>
                            <ion-input label="账号"></ion-input>
                        </ion-item>
                        <ion-item>
                            <ion-input type="password" label="密码"></ion-input>
                        </ion-item>
                    </ion-list>
                </ion-content>
            </ion-modal>
        </ion-content>
    </ion-page>
</template>
  
<script setup lang="ts">
const modal = ref();
const input = ref();
const alertButtons = ['登录',{
    text: '取消',
    role: 'cancel',
    cssClass: 'cancelButton'
}];
 const alertInputs = [
    {
      placeholder: '账号',
    },
    {
      placeholder: '密码',
      attributes: {
        type: 'password',
      }
    }
  ];


const cancel = () => modal.value.$el.dismiss(null, 'cancel');

const confirm = () => {
    const name = input.value.$el.value;
    modal.value.$el.dismiss(name, 'confirm');
};

const onWillDismiss = (ev: CustomEvent<OverlayEventDetail>) => {
    if (ev.detail.role === 'confirm') {
        message.value = `Hello, ${ev.detail.data}!`;
    }
};

import { ref } from 'vue';
import { OverlayEventDetail } from '@ionic/core/components';
import {
    IonPage,
    IonContent,
    IonButton,
    IonButtons,
    IonHeader,
    IonInput,
    IonItem,
    IonLabel,
    IonList,
    IonModal,
    IonToggle,
    IonToolbar,
    IonTitle,
    IonAlert
} from '@ionic/vue';

</script>