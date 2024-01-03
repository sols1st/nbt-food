interface Comment {
    id?:number
    restaurantId?:number
    userId?:number
    userName?:string
    content?:string
    score?:number
    gmtCreate?:string
}

export type { Comment };
