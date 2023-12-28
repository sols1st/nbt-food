import Axios from "@/utils/axios";
import { User } from "@/models/user";

const UserService = {
  async addUser(u: User) {
    return (await Axios("/user/add", u, "post")) as string;
  },
  async removeUser(u: User) {
    return (await Axios("/user/delete/" + u.id, null, "delete")) as string;
  },
  async updateUser(u: User) {
    return (await Axios("/user/update", u, "put")) as string;
  },
  async listUser() {
    return (await Axios("/user/list", null, "get", false)) as User[];
  },
};

export default UserService;
