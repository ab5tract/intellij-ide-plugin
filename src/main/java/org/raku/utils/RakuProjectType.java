package org.raku.utils;

public enum RakuProjectType {
    PERL6_SCRIPT,
    PERL6_MODULE,
    PERL6_APPLICATION,
    CRO_WEB_APPLICATION;

    public static String getDescription(RakuProjectType type) {
        switch (type) {
            case PERL6_SCRIPT:
                return "Creates a stub script file, and nothing more.";
            case PERL6_MODULE:
                return "Creates a stub Raku module, consisting of a module for application logic and a test file. " +
                        "Includes a META6.json so the module can be installed with its dependencies and distributed.";
            case PERL6_APPLICATION:
                return "Creates a stub Raku application, consisting of a script, a module for application logic, and a test file. " +
                        "Includes a META6.json so the application can be installed with its dependencies and distributed.";
            case CRO_WEB_APPLICATION:
                return "Creates a stub Cro web application";
        }
        return null;
    }

    public static String toTypeLabel(RakuProjectType type) {
        switch (type) {
            case PERL6_SCRIPT:
                return "Raku script";
            case PERL6_MODULE:
                return "Raku module";
            case PERL6_APPLICATION:
                return "Raku application";
            default:
                return "Cro web application";
        }
    }

    public static RakuProjectType fromTypeLabel(String label) {
        if (label == null)
            return PERL6_SCRIPT;
        switch (label) {
            case "Raku script":
                return PERL6_SCRIPT;
            case "Raku module":
                return PERL6_MODULE;
            case "Raku application":
                return PERL6_APPLICATION;
            default:
                return CRO_WEB_APPLICATION;
        }
    }
}