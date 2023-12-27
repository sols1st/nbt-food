import { h, render } from "vue";
import { IonToast } from "@ionic/vue";

const divVNode = h("div", { class: "fixed inset-x-0 top-0" });
render(divVNode, document.body);
const div = divVNode.el;

const Toast = (message: string) => {
  const comVNode = h(IonToast, { message: message,'is-open':true });
  if (div != null) {
    render(comVNode, div as any);
    setTimeout(() => {
      render(null, div as any);
    }, 3500);
  }
};

export default Toast;
