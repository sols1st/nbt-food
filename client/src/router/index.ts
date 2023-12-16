import { createRouter, createWebHistory } from "@ionic/vue-router";
import { RouteRecordRaw } from "vue-router";
import TabBar from "@/components/TapBar.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    redirect: "/list",
  },
  {
    path: "/detail/:id",
    component: () => import("@/views/DetailPage.vue"),
  },
  {
    path: "/",
    component: TabBar,
    children: [
      {
        path: "",
        redirect: "/list",
      },
      {
        path: "random",
        component: () => import("@/views/RandomPage.vue"),
      },
      {
        path: "list",
        component: () => import("@/views/ListPage.vue"),
      },
      {
        path: "login",
        component: () => import("@/views/LoginPage.vue"),
      },
      {
        path: "search",
        component: () => import("@/views/SearchPage.vue"),
      },
      {
        path: "manage",
        component: () => import("@/views/ManagePage.vue"),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
