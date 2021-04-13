package ru.bspb.restApiData;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListUsers {

    @JsonProperty("per_page")
    private Integer perPage;

    @JsonProperty("total")
    private Integer countUsers;

    @JsonProperty("data")
    private List<User> userList;

    @JsonProperty("page")
    private Integer page;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("support")
    private Support support;

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setCountUsers(Integer countUsers) {
        this.countUsers = countUsers;
    }

    public Integer getCountUsers() {
        return countUsers;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public Support getSupport() {
        return support;
    }

    @Override
    public String toString() {
        return
                "Response{" +
                        "per_page = '" + perPage + '\'' +
                        ",total = '" + countUsers + '\'' +
                        ",data = '" + userList + '\'' +
                        ",page = '" + page + '\'' +
                        ",total_pages = '" + totalPages + '\'' +
                        ",support = '" + support + '\'' +
                        "}";
    }


    public static class User {

        @JsonProperty("last_name")
        private String lastName;

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("avatar")
        private String avatar;

        @JsonProperty("first_name")
        private String firstName;

        @JsonProperty("email")
        private String email;

        @JsonProperty("createdAt")
        private String createdAt;

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        @Override
        public String toString() {
            return
                    "\nПользователь: {" +
                            "ID = '" + id + '\'' +
                            ", Фамилия = '" + lastName + '\'' +
                            ", Имя = '" + firstName + '\'' +
                            ", Фото = '" + avatar + '\'' +
                            ", eMail = '" + email + '\'' +
                            ", Дата создания = '" + createdAt + '\'' +
                            "}";
        }


    }


    public static class Support {

        @JsonProperty("text")
        private String text;

        @JsonProperty("url")
        private String url;

        public void setText(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }


}