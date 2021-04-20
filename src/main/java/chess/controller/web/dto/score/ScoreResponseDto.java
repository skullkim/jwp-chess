package chess.controller.web.dto.score;

public class ScoreResponseDto {

    private double whiteScore;
    private double blackScore;

    public ScoreResponseDto() {
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public void setWhiteScore(double whiteScore) {
        this.whiteScore = whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public void setBlackScore(double blackScore) {
        this.blackScore = blackScore;
    }
}