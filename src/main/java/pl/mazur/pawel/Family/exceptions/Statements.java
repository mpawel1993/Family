package pl.mazur.pawel.Family.exceptions;

public enum Statements {
    FAMILY_NOT_FOUND_STATEMENT("family_not_found"),
    FATHER_NOT_FOUND_STATEMENT("father_not_found"),
    MOTHER_NOT_FOUND_STATEMENT("mather_not_found"),
    CHILD_NOT_FOUND_STATEMENT("child_not_found");

    private final String stat;

    Statements(String statement) {
        stat = statement;
    }

    @Override
    public String toString() {
        return this.stat;
    }
}
