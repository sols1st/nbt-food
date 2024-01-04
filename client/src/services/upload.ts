import Axios from "@/utils/axios";

const UploadService = {
  async uploadImg(image: string) {
    return (await Axios("/base64Upload", { image: image }, "post")) as string;
  },
};

export default UploadService;
