import Axios from "@/utils/axios";
import { CommentPage } from "@/models/page";
import { Comment } from "@/models/comment";

const CommentService = {
  async addComment(c: Comment) {
    return (await Axios("/comment/add", c, "post")) as string;
  },
  async removeComment(c: Comment) {
    return (await Axios("/comment/delete/" + c.id, null, "delete")) as string;
  },
  async updateComment(c: Comment) {
    return (await Axios("/comment/update", c, "put")) as string;
  },
  // 分页条件查询
  async pageComment(body: CommentPage) {
    return (await Axios("/comment/page", body, "post", false)) as {
      records: Comment[];
      totalRecords: number;
      totalPages: number;
    };
  },
};

export default CommentService;
