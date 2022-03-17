package com.example.data.remotely.wishes;

public class WishesRequests {
    public class GetWishes {
        public int from;
        public int to;

        public GetWishes(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public class GetWish {
        public int id;

        public GetWish(int id) {
            this.id = id;
        }
    }

    public class PostWish {
        public int id;
        public String name;
        public String version;
        public String poster;
        public int rate_5;
        public int rate_4;

        public PostWish(int id, String name, String version, String poster, int rate_5, int rate_4){
            this.id = id;
            this.name = name;
            this.version = version;
            this.poster = poster;
            this.rate_5 = rate_5;
            this.rate_4 = rate_4;
        }
    }

    public class DeleteWish extends GetWish {
        public DeleteWish(int id) {
            super(id);
        }
    }

    public class PatchWish extends PostWish {
        public PatchWish(int id, int modified_at, int created_at, String name, String version, String poster, int rate_5, int rate_4){
            super(id, name, version, poster, rate_5, rate_4);
        }
    }
}
