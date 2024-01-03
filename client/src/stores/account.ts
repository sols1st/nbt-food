import { UserInfoResponse } from "@logto/vue";
import { defineStore } from "pinia";

export const useAccountStore = defineStore("account", {
  state() {
    return {
      account: {} as UserInfoResponse,
      token: "",
    };
  },
  persist: {
    storage: localStorage,
    paths: ["token", "account"],
  },
  actions: {
  },
});
