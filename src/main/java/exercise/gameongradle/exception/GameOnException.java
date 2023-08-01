package exercise.gameongradle.exception;

import org.springframework.http.HttpStatus;

public class GameOnException {
    private static final String COMMENT_NOT_FOUND_MSG_KEY = "CommentNotExisted";
    private static final String COMMENT_NOT_FOUND_MSG = "Comment Not Found";

    private static final String DEVELOPER_NOT_FOUND_MSG_KEY = "DeveloperNotExisted";
    private static final String DEVELOPER_NOT_FOUND_MSG = "Developer Not Found";

    private static final String GAME_NOT_FOUND_MSG_KEY = "GameNotExisted";
    private static final String GAME_NOT_FOUND_MSG = "Game Not Found";

    private static final String GAME_GENRE_NOT_FOUND_MSG_KEY = "GameGenreNotExisted";
    private static final String GAME_GENRE_NOT_FOUND_MSG = "Game Genre Not Found";

    private static final String GAME_IMAGE_NOT_FOUND_MSG_KEY = "GameImageNotExisted";
    private static final String GAME_IMAGE_NOT_FOUND_MSG = "Game Image Not Found";

    private static final String GAME_SUB_GENRE_NOT_FOUND_MSG_KEY = "GameSubGenreNotExisted";
    private static final String GAME_SUB_GENRE_NOT_FOUND_MSG = "Game Sub Genre Not Found";

    private static final String GENRE_NOT_FOUND_MSG_KEY = "GenreNotExisted";
    private static final String GENRE_NOT_FOUND_MSG = "Genre Not Found";

    private static final String PUBLISHER_NOT_FOUND_MSG_KEY = "PublisherNotExisted";
    private static final String PUBLISHER_NOT_FOUND_MSG = "Publisher Not Found";

    private static final String RATING_NOT_FOUND_MSG_KEY = "RatingNotExisted";
    private static final String RATING_NOT_FOUND_MSG = "Rating Not Found";

    private static final String RECEIPT_NOT_FOUND_MSG_KEY = "ReceiptNotExisted";
    private static final String RECEIPT_NOT_FOUND_MSG = "Receipt Not Found";

    private static final String RECEIPT_DETAILS_NOT_FOUND_MSG_KEY = "ReceiptDetailsNotExisted";
    private static final String RECEIPT_DETAILS_NOT_FOUND_MSG = "Receipt Details Not Found";

    private static final String SUB_GENRE_NOT_FOUND_MSG_KEY = "SubGenreNotExisted";
    private static final String SUB_GENRE_NOT_FOUND_MSG = "Sub Genre Not Found";

    private static final String USER_NOT_FOUND_MSG_KEY = "UserNotExisted";
    private static final String USER_NOT_FOUND_MSG = "User Not Found";

    private static final String USER_ROLE_ASSIGNMENT_NOT_FOUND_MSG_KEY = "UserRoleAssignmentNotExisted";
    private static final String USER_ROLE_ASSIGNMENT_NOT_FOUND_MSG = "User Role Assignment Not Found";


    private static final String GAME_CODE_NOT_FOUND_MSG_KEY = "GameCodeNotExisted";
    private static final String GAME_CODE_NOT_FOUND_MSG = "Game Code NOt Found";
    public static ResponseException notFound(String msgKey, String msg) {
        return new ResponseException(msgKey, msg, HttpStatus.NOT_FOUND);
    }

    public static ResponseException badRequest(String msgKey, String msg) {
        return new ResponseException(msgKey, msg, HttpStatus.BAD_REQUEST);
    }

    public static ResponseException internalServerError(String msgKey, String msg) {
        return new ResponseException(msgKey, msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseException CommentNotFound() {
        return notFound(COMMENT_NOT_FOUND_MSG_KEY, COMMENT_NOT_FOUND_MSG);
    }

    public static ResponseException DeveloperNotFound() {
        return notFound(DEVELOPER_NOT_FOUND_MSG_KEY, DEVELOPER_NOT_FOUND_MSG);
    }

    public static ResponseException GameNotFound() {
        return notFound(GAME_NOT_FOUND_MSG_KEY, GAME_NOT_FOUND_MSG);
    }

    public static ResponseException GameGenreNotFound() {
        return notFound(GAME_GENRE_NOT_FOUND_MSG_KEY, GAME_GENRE_NOT_FOUND_MSG);
    }

    public static ResponseException GameImageNotFound() {
        return notFound(GAME_IMAGE_NOT_FOUND_MSG_KEY, GAME_IMAGE_NOT_FOUND_MSG);
    }

    public static ResponseException GameSubGenreNotFound() {
        return notFound(GAME_SUB_GENRE_NOT_FOUND_MSG_KEY, GAME_SUB_GENRE_NOT_FOUND_MSG);
    }

    public static ResponseException GenreNotFound() {
        return notFound(GENRE_NOT_FOUND_MSG_KEY, GENRE_NOT_FOUND_MSG);
    }

    public static ResponseException PublisherNotFound() {
        return notFound(PUBLISHER_NOT_FOUND_MSG_KEY, PUBLISHER_NOT_FOUND_MSG);
    }

    public static ResponseException RatingNotFound() {
        return notFound(RATING_NOT_FOUND_MSG_KEY, RATING_NOT_FOUND_MSG);
    }

    public static ResponseException ReceiptNotFound() {
        return notFound(RECEIPT_NOT_FOUND_MSG_KEY, RECEIPT_NOT_FOUND_MSG);
    }

    public static ResponseException ReceiptDetailsNotFound() {
        return notFound(RECEIPT_DETAILS_NOT_FOUND_MSG_KEY, RECEIPT_DETAILS_NOT_FOUND_MSG);
    }

    public static ResponseException SubGenreNotFound() {
        return notFound(SUB_GENRE_NOT_FOUND_MSG_KEY, SUB_GENRE_NOT_FOUND_MSG);
    }

    public static ResponseException UserNotFound() {
        return notFound(USER_NOT_FOUND_MSG_KEY, USER_NOT_FOUND_MSG);
    }

    public static ResponseException UserRoleAssignmentNotFound() {
        return notFound(USER_ROLE_ASSIGNMENT_NOT_FOUND_MSG_KEY, USER_ROLE_ASSIGNMENT_NOT_FOUND_MSG);
    }

    public static ResponseException GameCodeNotFound() {
        return notFound(GAME_CODE_NOT_FOUND_MSG_KEY, GAME_CODE_NOT_FOUND_MSG);
    }
}
