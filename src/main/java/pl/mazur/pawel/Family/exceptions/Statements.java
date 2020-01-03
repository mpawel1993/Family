package pl.mazur.pawel.Family.exceptions;

public enum Statements {
    FAMILY_NOT_FOUND_STATEMENT("family_not_found"),
    FATHER_NOT_FOUND_STATEMENT("father_not_found"),
    MOTHER_NOT_FOUND_STATEMENT("mather_not_found"),
    CHILD_NOT_FOUND_STATEMENT("child_not_found"),
    FAMILY_HAVE_FATHER_STATEMENT("family_already_have_one_father"),
    FAMILY_HAVE_MOTHER_STATEMENT("family_already_have_one_mother"),
    ALREADY_EXISTING_FATHER_STATEMENT("new_father_cannot_already_exist"),
    ALREADY_EXISTING_MOTHER_STATEMENT("new_mother_cannot_already_exist");

    private final String stat;

    Statements(String statement) {
        stat = statement;
    }

    @Override
    public String toString() {
        return this.stat;
    }
}
