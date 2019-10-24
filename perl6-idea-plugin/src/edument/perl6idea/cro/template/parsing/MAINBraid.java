package edument.perl6idea.cro.template.parsing;
import edument.perl6idea.parsing.Cursor;

public class MAINBraid extends Cursor<MAINBraid> {

    public int runRule() {
        int result;
        switch (this.ruleNumber) {
        case 0:
            result = this._0_TOP();
            break;
        case 1:
            result = this._1_bogus_end();
            break;
        case 2:
            result = this._2_sequence_element();
            break;
        case 3:
            result = this._3_sequence_element_group();
            break;
        case 4:
            result = this._4_sequence_element_literal_text();
            break;
        case 5:
            result = this._5_sequence_element_literal_open_tag();
            break;
        case 6:
            result = this._6_sequence_element_literal_close_tag();
            break;
        case 7:
            result = this._7_literal_tag_name();
            break;
        case 8:
            result = this._8_literal_tag_attribute();
            break;
        case 9:
            result = this._9_literal_tag_attribute_value();
            break;
        case 10:
            result = this._10_sigil();
            break;
        case 11:
            result = this._11_sigil_tag();
            break;
        case 12:
            result = this._12_sigil_tag_topic();
            break;
        case 13:
            result = this._13_sigil_tag_variable();
            break;
        case 14:
            result = this._14_sigil_tag_iteration();
            break;
        case 15:
            result = this._15_sigil_tag_condition();
            break;
        case 16:
            result = this._16_block();
            break;
        case 17:
            result = this._17_sigil_tag_call();
            break;
        case 18:
            result = this._18_sigil_tag_sub();
            break;
        case 19:
            result = this._19_sigil_tag_macro();
            break;
        case 20:
            result = this._20_sigil_tag_body();
            break;
        case 21:
            result = this._21_sigil_tag_use();
            break;
        case 22:
            result = this._22_sigil_tag_apply();
            break;
        case 23:
            result = this._23_signature();
            break;
        case 24:
            result = this._24_parameter();
            break;
        case 25:
            result = this._25_arglist();
            break;
        case 26:
            result = this._26_expression();
            break;
        case 27:
            result = this._27_nextterm();
            break;
        case 28:
            result = this._28_infixish();
            break;
        case 29:
            result = this._29_infix();
            break;
        case 30:
            result = this._30_term();
            break;
        case 31:
            result = this._31_single_quote_string();
            break;
        case 32:
            result = this._32_int();
            break;
        case 33:
            result = this._33_rat();
            break;
        case 34:
            result = this._34_num();
            break;
        case 35:
            result = this._35_variable();
            break;
        case 36:
            result = this._36_deref_term();
            break;
        case 37:
            result = this._37_parenthesized_expression();
            break;
        case 38:
            result = this._38_deref();
            break;
        case 39:
            result = this._39_deref_item();
            break;
        case 40:
            result = this._40_deref_item_smart();
            break;
        case 41:
            result = this._41_ws();
            break;
        case 42:
            result = this._42_hws();
            break;
        case 43:
            result = this._43_tlt();
            break;
        case 44:
            result = this._44_tgt();
            break;
        case 45:
            result = this._45_tclose();
            break;
        case 46:
            result = this._46_dot();
            break;
        case 47:
            result = this._47_lt();
            break;
        case 48:
            result = this._48_gt();
            break;
        case 49:
            result = this._49_close();
            break;
        case 50:
            result = this._50_iter_sigil();
            break;
        case 51:
            result = this._51_cond_sigil();
            break;
        case 52:
            result = this._52_call_sigil();
            break;
        case 53:
            result = this._53_decl_sigil();
            break;
        case 54:
            result = this._54_apply_sigil();
            break;
        case 55:
            result = this._55_identifier();
            break;
        case 56:
            result = this._56_ident();
            break;
        case 57:
            result = this.___lookahead_0();
            break;
        case 58:
            result = this.___lookahead_1();
            break;
        case 59:
            result = this.___lookahead_2();
            break;
        case 60:
            result = this.___lookahead_3();
            break;
        case 61:
            result = this.___lookahead_4();
            break;
        case 62:
            result = this.___lookahead_5();
            break;
        case 63:
            result = this.___lookahead_6();
            break;
        case 64:
            result = this.___lookahead_7();
            break;
        case 65:
            result = this.___lookahead_8();
            break;
        case 66:
            result = this.___lookahead_9();
            break;
        case 67:
            result = this.___lookahead_10();
            break;
        case 68:
            result = this.___lookahead_11();
            break;
        case 69:
            result = this.___lookahead_12();
            break;
        case 70:
            result = this.___lookahead_13();
            break;
        case 71:
            result = this.___lookahead_14();
            break;
        case 72:
            result = this.___lookahead_15();
            break;
        case 73:
            result = this.___lookahead_16();
            break;
        case 74:
            result = this.___lookahead_17();
            break;
        case 75:
            result = this.___lookahead_18();
            break;
        case 76:
            result = this.___lookahead_19();
            break;
        case 77:
            result = this.___lookahead_20();
            break;
        case 78:
            result = this.___lookahead_21();
            break;
        case 79:
            result = this.___lookahead_22();
            break;
        default:
            throw new RuntimeException();

        }
        return result;
    }

    private int _0_TOP() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.bsMark(3);
                this.state = 1;
                break;
            case 1:
                this.setArgs();
                this.state = 2;
                return 2;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                rep = this.peekRep(3);
                ++rep;
                this.bsCommit(3);
                this.bsMark(3, rep);
                this.state = 1;
                continue;

            case 3:
                this.bsMark(6);
                this.state = 4;
                break;
            case 4:
                this.setArgs();
                this.state = 5;
                return 1;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(6);
                this.state = 6;
                continue;

            case 6:
                return -1;

            }
        }
    }

    private int _1_bogus_end() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.BAD_CHARACTER);
                this.bsFailMark(2);
                this.state = 1;
                break;
            case 1:
                if (!(this.anyChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(2);
                ++rep;
                this.bsCommit(2);
                this.bsMark(2, rep);
                this.state = 1;
                continue;

            case 2:
                this.state = 3;
                return -3;

            case 3:
                return -1;

            }
        }
    }

    private int _2_sequence_element() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.bsFailMark(8);
                this.bsMark(2);
                this.setArgs();
                this.state = 1;
                return 6;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 2:
                this.bsMark(4);
                this.setArgs();
                this.state = 3;
                return 5;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 4:
                this.bsMark(6);
                this.setArgs();
                this.state = 5;
                return 4;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 6:
                this.setArgs();
                this.state = 7;
                return 11;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 8;
                continue;

            case 8:
                return -1;

            }
        }
    }

    private int _3_sequence_element_group() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.bsMark(3);
                this.state = 1;
                break;
            case 1:
                this.setArgs();
                this.state = 2;
                return 2;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                rep = this.peekRep(3);
                ++rep;
                this.bsCommit(3);
                this.bsMark(3, rep);
                this.state = 1;
                continue;

            case 3:
                return -1;

            }
        }
    }

    private int _4_sequence_element_literal_text() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.LITERAL_TEXT);
                this.bsFailMark(2);
                this.state = 1;
                break;
            case 1:
                if (!(this.notInCharList("<"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(2);
                ++rep;
                this.bsCommit(2);
                this.bsMark(2, rep);
                this.state = 1;
                continue;

            case 2:
                this.state = 3;
                return -3;

            case 3:
                return -1;

            }
        }
    }

    private int _5_sequence_element_literal_open_tag() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                if (!(this.lookahead(57))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 1;
                return 47;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(16);
                this.state = 2;
                break;
            case 2:
                this.setArgs();
                this.state = 3;
                return 7;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 4;
                return 41;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(12);
                this.state = 5;
                break;
            case 5:
                this.bsFailMark(11);
                this.bsMark(8);
                this.setArgs();
                this.state = 6;
                return 8;

            case 6:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 7;
                return 41;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(11);
                this.state = 11;
                continue;

            case 8:
                this.setArgs();
                this.state = 9;
                return 11;

            case 9:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 10;
                return 41;

            case 10:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 11;
                continue;

            case 11:
                rep = this.peekRep(12);
                ++rep;
                this.bsCommit(12);
                this.bsMark(12, rep);
                this.state = 5;
                continue;

            case 12:
                this.bsMark(15);
                this.state = 13;
                break;
            case 13:
                this.setArgs();
                this.state = 14;
                return 48;

            case 14:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(15);
                this.state = 15;
                continue;

            case 15:
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 16:
                return -1;

            }
        }
    }

    private int _6_sequence_element_literal_close_tag() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                if (!(this.lookahead(60))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 1;
                return 47;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 2;
                return 49;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(9);
                this.state = 3;
                break;
            case 3:
                this.setArgs();
                this.state = 4;
                return 41;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 5;
                return 7;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(8);
                this.state = 6;
                break;
            case 6:
                this.setArgs();
                this.state = 7;
                return 48;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 8:
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 9:
                return -1;

            }
        }
    }

    private int _7_literal_tag_name() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.LITERAL_TAG_NAME);
                this.setArgs();
                this.state = 1;
                return 56;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 2;
                return -3;

            case 2:
                return -1;

            }
        }
    }

    private int _8_literal_tag_attribute() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.ATTRIBUTE_NAME);
                this.setArgs();
                this.state = 1;
                return 55;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 2;
                return -3;

            case 2:
                this.setArgs();
                this.state = 3;
                return 41;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(10);
                this.state = 4;
                break;
            case 4:
                this.startToken(CroTemplateTokenTypes.ATTRIBUTE_EQUALS);
                if (!(this.literal("="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 5;
                return -3;

            case 5:
                this.setArgs();
                this.state = 6;
                return 41;

            case 6:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(9);
                this.state = 7;
                break;
            case 7:
                this.setArgs();
                this.state = 8;
                return 9;

            case 8:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 9:
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 10:
                return -1;

            }
        }
    }

    private int _9_literal_tag_attribute_value() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.bsFailMark(34);
                this.bsMark(15);
                this.startToken(CroTemplateTokenTypes.ATTRIBUTE_QUOTE);
                if (!(this.literal("\""))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                this.bsMark(11);
                this.state = 2;
                break;
            case 2:
                this.bsFailMark(10);
                this.bsMark(6);
                this.startToken(CroTemplateTokenTypes.ATTRIBUTE_VALUE);
                this.bsFailMark(4);
                this.state = 3;
                break;
            case 3:
                if (!(this.notInCharList("\"<"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(4);
                ++rep;
                this.bsCommit(4);
                this.bsMark(4, rep);
                this.state = 3;
                continue;

            case 4:
                this.state = 5;
                return -3;

            case 5:
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 6:
                this.bsMark(8);
                this.setArgs();
                this.state = 7;
                return 11;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 8:
                this.startToken(CroTemplateTokenTypes.ATTRIBUTE_VALUE);
                if (!(this.literal("<"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 9;
                return -3;

            case 9:
                this.state = 10;
                continue;

            case 10:
                rep = this.peekRep(11);
                ++rep;
                this.bsCommit(11);
                this.bsMark(11, rep);
                this.state = 2;
                continue;

            case 11:
                this.bsMark(14);
                this.state = 12;
                break;
            case 12:
                this.startToken(CroTemplateTokenTypes.ATTRIBUTE_QUOTE);
                if (!(this.literal("\""))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 13;
                return -3;

            case 13:
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 14:
                this.bsCommit(34);
                this.state = 34;
                continue;

            case 15:
                this.bsMark(30);
                this.startToken(CroTemplateTokenTypes.ATTRIBUTE_QUOTE);
                if (!(this.literal("'"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 16;
                return -3;

            case 16:
                this.bsMark(26);
                this.state = 17;
                break;
            case 17:
                this.bsFailMark(25);
                this.bsMark(21);
                this.startToken(CroTemplateTokenTypes.ATTRIBUTE_VALUE);
                this.bsFailMark(19);
                this.state = 18;
                break;
            case 18:
                if (!(this.notInCharList("'<"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(19);
                ++rep;
                this.bsCommit(19);
                this.bsMark(19, rep);
                this.state = 18;
                continue;

            case 19:
                this.state = 20;
                return -3;

            case 20:
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 21:
                this.bsMark(23);
                this.setArgs();
                this.state = 22;
                return 11;

            case 22:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 23:
                this.startToken(CroTemplateTokenTypes.ATTRIBUTE_VALUE);
                if (!(this.literal("<"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 24;
                return -3;

            case 24:
                this.state = 25;
                continue;

            case 25:
                rep = this.peekRep(26);
                ++rep;
                this.bsCommit(26);
                this.bsMark(26, rep);
                this.state = 17;
                continue;

            case 26:
                this.bsMark(29);
                this.state = 27;
                break;
            case 27:
                this.startToken(CroTemplateTokenTypes.ATTRIBUTE_QUOTE);
                if (!(this.literal("'"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 28;
                return -3;

            case 28:
                this.bsCommit(29);
                this.state = 29;
                continue;

            case 29:
                this.bsCommit(34);
                this.state = 34;
                continue;

            case 30:
                this.startToken(CroTemplateTokenTypes.ATTRIBUTE_VALUE);
                this.bsMark(32);
                this.state = 31;
                break;
            case 31:
                if (!(this.notSpaceChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(32);
                ++rep;
                this.bsCommit(32);
                this.bsMark(32, rep);
                this.state = 31;
                continue;

            case 32:
                this.state = 33;
                return -3;

            case 33:
                this.state = 34;
                continue;

            case 34:
                return -1;

            }
        }
    }

    private int _10_sigil() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.bsFailMark(2);
                this.bsMark(1);
                if (!(this.inCharList(".$@&:|"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(2);
                this.state = 2;
                continue;

            case 1:
                if (!(this.inCharList("?!"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                if (!(this.inCharList(".$>{"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 2;
                continue;

            case 2:
                return -1;

            }
        }
    }

    private int _11_sigil_tag() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.bsFailMark(20);
                this.bsMark(2);
                this.setArgs();
                this.state = 1;
                return 12;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(20);
                this.state = 20;
                continue;

            case 2:
                this.bsMark(4);
                this.setArgs();
                this.state = 3;
                return 13;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(20);
                this.state = 20;
                continue;

            case 4:
                this.bsMark(6);
                this.setArgs();
                this.state = 5;
                return 14;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(20);
                this.state = 20;
                continue;

            case 6:
                this.bsMark(8);
                this.setArgs();
                this.state = 7;
                return 15;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(20);
                this.state = 20;
                continue;

            case 8:
                this.bsMark(10);
                this.setArgs();
                this.state = 9;
                return 17;

            case 9:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(20);
                this.state = 20;
                continue;

            case 10:
                this.bsMark(12);
                this.setArgs();
                this.state = 11;
                return 18;

            case 11:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(20);
                this.state = 20;
                continue;

            case 12:
                this.bsMark(14);
                this.setArgs();
                this.state = 13;
                return 19;

            case 13:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(20);
                this.state = 20;
                continue;

            case 14:
                this.bsMark(16);
                this.setArgs();
                this.state = 15;
                return 20;

            case 15:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(20);
                this.state = 20;
                continue;

            case 16:
                this.bsMark(18);
                this.setArgs();
                this.state = 17;
                return 22;

            case 17:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(20);
                this.state = 20;
                continue;

            case 18:
                this.setArgs();
                this.state = 19;
                return 21;

            case 19:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 20;
                continue;

            case 20:
                return -1;

            }
        }
    }

    private int _12_sigil_tag_topic() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                if (!(this.lookahead(62))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 1;
                return 43;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 2;
                return 46;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(8);
                this.state = 3;
                break;
            case 3:
                this.setArgs();
                this.state = 4;
                return 38;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(7);
                this.state = 5;
                break;
            case 5:
                this.setArgs();
                this.state = 6;
                return 44;

            case 6:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(7);
                this.state = 7;
                continue;

            case 7:
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 8:
                return -1;

            }
        }
    }

    private int _13_sigil_tag_variable() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                if (!(this.lookahead(63))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 1;
                return 43;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.startToken(CroTemplateTokenTypes.VARIABLE_NAME);
                if (!(this.literal("$"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsMark(4);
                this.state = 2;
                break;
            case 2:
                this.setArgs();
                this.state = 3;
                return 55;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(4);
                this.state = 4;
                continue;

            case 4:
                this.state = 5;
                return -3;

            case 5:
                this.bsMark(14);
                this.state = 6;
                break;
            case 6:
                this.bsMark(12);
                this.state = 7;
                break;
            case 7:
                this.setArgs();
                this.state = 8;
                return 46;

            case 8:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(11);
                this.state = 9;
                break;
            case 9:
                this.setArgs();
                this.state = 10;
                return 38;

            case 10:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(11);
                this.state = 11;
                continue;

            case 11:
                this.bsCommit(12);
                this.state = 12;
                continue;

            case 12:
                this.setArgs();
                this.state = 13;
                return 44;

            case 13:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 14:
                return -1;

            }
        }
    }

    private int _14_sigil_tag_iteration() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                if (!(this.lookahead(64))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 1;
                return 43;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 2;
                return 50;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(17);
                this.state = 3;
                break;
            case 3:
                this.setArgs();
                this.state = 4;
                return 38;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(16);
                this.state = 5;
                break;
            case 5:
                this.setArgs();
                this.state = 6;
                return 44;

            case 6:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 7;
                return 3;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(15);
                this.state = 8;
                break;
            case 8:
                if (!(this.lookahead(65))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 9;
                return 43;

            case 9:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 10;
                return 45;

            case 10:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 11;
                return 50;

            case 11:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(14);
                this.state = 12;
                break;
            case 12:
                this.setArgs();
                this.state = 13;
                return 44;

            case 13:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 14:
                this.bsCommit(15);
                this.state = 15;
                continue;

            case 15:
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 16:
                this.bsCommit(17);
                this.state = 17;
                continue;

            case 17:
                return -1;

            }
        }
    }

    private int _15_sigil_tag_condition() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                if (!(this.lookahead(66))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 1;
                return 43;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 2;
                return 51;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(23);
                this.state = 3;
                break;
            case 3:
                this.bsFailMark(10);
                this.bsMark(8);
                this.setArgs();
                this.state = 4;
                return 46;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(7);
                this.state = 5;
                break;
            case 5:
                this.setArgs();
                this.state = 6;
                return 38;

            case 6:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(7);
                this.state = 7;
                continue;

            case 7:
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 8:
                this.setArgs();
                this.state = 9;
                return 16;

            case 9:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 10;
                continue;

            case 10:
                this.bsMark(22);
                this.state = 11;
                break;
            case 11:
                this.setArgs();
                this.state = 12;
                return 44;

            case 12:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 13;
                return 3;

            case 13:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(21);
                this.state = 14;
                break;
            case 14:
                if (!(this.lookahead(67))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 15;
                return 43;

            case 15:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 16;
                return 45;

            case 16:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 17;
                return 51;

            case 17:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(20);
                this.state = 18;
                break;
            case 18:
                this.setArgs();
                this.state = 19;
                return 44;

            case 19:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(20);
                this.state = 20;
                continue;

            case 20:
                this.bsCommit(21);
                this.state = 21;
                continue;

            case 21:
                this.bsCommit(22);
                this.state = 22;
                continue;

            case 22:
                this.bsCommit(23);
                this.state = 23;
                continue;

            case 23:
                return -1;

            }
        }
    }

    private int _16_block() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.OPEN_CURLY);
                if (!(this.literal("{"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                this.setArgs();
                this.state = 2;
                return 41;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(9);
                this.state = 3;
                break;
            case 3:
                this.setArgs();
                this.state = 4;
                return 26;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 5;
                return 41;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(8);
                this.state = 6;
                break;
            case 6:
                this.startToken(CroTemplateTokenTypes.CLOSE_CURLY);
                if (!(this.literal("}"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 7;
                return -3;

            case 7:
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 8:
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 9:
                return -1;

            }
        }
    }

    private int _17_sigil_tag_call() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                if (!(this.lookahead(68))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 1;
                return 43;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 2;
                return 52;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(18);
                this.state = 3;
                break;
            case 3:
                this.startToken(CroTemplateTokenTypes.SUB_NAME);
                this.setArgs();
                this.state = 4;
                return 55;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 5;
                return -3;

            case 5:
                this.bsMark(8);
                this.state = 6;
                break;
            case 6:
                this.setArgs();
                this.state = 7;
                return 42;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 8:
                this.bsMark(11);
                this.state = 9;
                break;
            case 9:
                this.setArgs();
                this.state = 10;
                return 25;

            case 10:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(11);
                this.state = 11;
                continue;

            case 11:
                this.bsMark(14);
                this.state = 12;
                break;
            case 12:
                this.setArgs();
                this.state = 13;
                return 42;

            case 13:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 14:
                this.bsMark(17);
                this.state = 15;
                break;
            case 15:
                this.setArgs();
                this.state = 16;
                return 44;

            case 16:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(17);
                this.state = 17;
                continue;

            case 17:
                this.bsCommit(18);
                this.state = 18;
                continue;

            case 18:
                return -1;

            }
        }
    }

    private int _18_sigil_tag_sub() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                if (!(this.lookahead(69))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 1;
                return 43;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 2;
                return 53;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.startToken(CroTemplateTokenTypes.DECL_OPENER);
                if (!(this.literal("sub"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 3;
                return -3;

            case 3:
                this.bsMark(34);
                this.state = 4;
                break;
            case 4:
                this.setArgs();
                this.state = 5;
                return 42;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(33);
                this.state = 6;
                break;
            case 6:
                this.startToken(CroTemplateTokenTypes.SUB_NAME);
                this.setArgs();
                this.state = 7;
                return 55;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 8;
                return -3;

            case 8:
                this.bsMark(11);
                this.state = 9;
                break;
            case 9:
                this.setArgs();
                this.state = 10;
                return 42;

            case 10:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(11);
                this.state = 11;
                continue;

            case 11:
                this.bsMark(14);
                this.state = 12;
                break;
            case 12:
                this.setArgs();
                this.state = 13;
                return 23;

            case 13:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 14:
                this.bsMark(17);
                this.state = 15;
                break;
            case 15:
                this.setArgs();
                this.state = 16;
                return 42;

            case 16:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(17);
                this.state = 17;
                continue;

            case 17:
                this.bsMark(32);
                this.state = 18;
                break;
            case 18:
                this.setArgs();
                this.state = 19;
                return 44;

            case 19:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 20;
                return 3;

            case 20:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(31);
                this.state = 21;
                break;
            case 21:
                if (!(this.lookahead(70))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 22;
                return 43;

            case 22:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 23;
                return 45;

            case 23:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 24;
                return 53;

            case 24:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(27);
                this.state = 25;
                break;
            case 25:
                this.startToken(CroTemplateTokenTypes.DECL_OPENER);
                if (!(this.literal("sub"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 26;
                return -3;

            case 26:
                this.bsCommit(27);
                this.state = 27;
                continue;

            case 27:
                this.bsMark(30);
                this.state = 28;
                break;
            case 28:
                this.setArgs();
                this.state = 29;
                return 44;

            case 29:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(30);
                this.state = 30;
                continue;

            case 30:
                this.bsCommit(31);
                this.state = 31;
                continue;

            case 31:
                this.bsCommit(32);
                this.state = 32;
                continue;

            case 32:
                this.bsCommit(33);
                this.state = 33;
                continue;

            case 33:
                this.bsCommit(34);
                this.state = 34;
                continue;

            case 34:
                return -1;

            }
        }
    }

    private int _19_sigil_tag_macro() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                if (!(this.lookahead(71))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 1;
                return 43;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 2;
                return 53;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.startToken(CroTemplateTokenTypes.DECL_OPENER);
                if (!(this.literal("macro"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 3;
                return -3;

            case 3:
                this.bsMark(34);
                this.state = 4;
                break;
            case 4:
                this.setArgs();
                this.state = 5;
                return 42;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(33);
                this.state = 6;
                break;
            case 6:
                this.startToken(CroTemplateTokenTypes.MACRO_NAME);
                this.setArgs();
                this.state = 7;
                return 55;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 8;
                return -3;

            case 8:
                this.bsMark(11);
                this.state = 9;
                break;
            case 9:
                this.setArgs();
                this.state = 10;
                return 42;

            case 10:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(11);
                this.state = 11;
                continue;

            case 11:
                this.bsMark(14);
                this.state = 12;
                break;
            case 12:
                this.setArgs();
                this.state = 13;
                return 23;

            case 13:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 14:
                this.bsMark(17);
                this.state = 15;
                break;
            case 15:
                this.setArgs();
                this.state = 16;
                return 42;

            case 16:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(17);
                this.state = 17;
                continue;

            case 17:
                this.bsMark(32);
                this.state = 18;
                break;
            case 18:
                this.setArgs();
                this.state = 19;
                return 44;

            case 19:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 20;
                return 3;

            case 20:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(31);
                this.state = 21;
                break;
            case 21:
                if (!(this.lookahead(72))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 22;
                return 43;

            case 22:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 23;
                return 45;

            case 23:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 24;
                return 53;

            case 24:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(27);
                this.state = 25;
                break;
            case 25:
                this.startToken(CroTemplateTokenTypes.DECL_OPENER);
                if (!(this.literal("macro"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 26;
                return -3;

            case 26:
                this.bsCommit(27);
                this.state = 27;
                continue;

            case 27:
                this.bsMark(30);
                this.state = 28;
                break;
            case 28:
                this.setArgs();
                this.state = 29;
                return 44;

            case 29:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(30);
                this.state = 30;
                continue;

            case 30:
                this.bsCommit(31);
                this.state = 31;
                continue;

            case 31:
                this.bsCommit(32);
                this.state = 32;
                continue;

            case 32:
                this.bsCommit(33);
                this.state = 33;
                continue;

            case 33:
                this.bsCommit(34);
                this.state = 34;
                continue;

            case 34:
                return -1;

            }
        }
    }

    private int _20_sigil_tag_body() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                if (!(this.lookahead(73))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 1;
                return 43;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 2;
                return 53;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.startToken(CroTemplateTokenTypes.DECL_OPENER);
                if (!(this.literal("body"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 3;
                return -3;

            case 3:
                this.bsMark(9);
                this.state = 4;
                break;
            case 4:
                this.bsMark(7);
                this.state = 5;
                break;
            case 5:
                this.setArgs();
                this.state = 6;
                return 42;

            case 6:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(7);
                this.state = 7;
                continue;

            case 7:
                this.setArgs();
                this.state = 8;
                return 44;

            case 8:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 9:
                return -1;

            }
        }
    }

    private int _21_sigil_tag_use() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                if (!(this.lookahead(74))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 1;
                return 43;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 2;
                return 53;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.startToken(CroTemplateTokenTypes.DECL_OPENER);
                if (!(this.literal("use"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 3;
                return -3;

            case 3:
                this.bsMark(15);
                this.state = 4;
                break;
            case 4:
                this.setArgs();
                this.state = 5;
                return 42;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(14);
                this.state = 6;
                break;
            case 6:
                this.setArgs();
                this.state = 7;
                return 31;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(10);
                this.state = 8;
                break;
            case 8:
                this.setArgs();
                this.state = 9;
                return 42;

            case 9:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 10:
                this.bsMark(13);
                this.state = 11;
                break;
            case 11:
                this.setArgs();
                this.state = 12;
                return 44;

            case 12:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(13);
                this.state = 13;
                continue;

            case 13:
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 14:
                this.bsCommit(15);
                this.state = 15;
                continue;

            case 15:
                return -1;

            }
        }
    }

    private int _22_sigil_tag_apply() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                if (!(this.lookahead(75))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 1;
                return 43;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 2;
                return 54;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(27);
                this.state = 3;
                break;
            case 3:
                this.startToken(CroTemplateTokenTypes.MACRO_NAME);
                this.setArgs();
                this.state = 4;
                return 55;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 5;
                return -3;

            case 5:
                this.bsMark(8);
                this.state = 6;
                break;
            case 6:
                this.setArgs();
                this.state = 7;
                return 42;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 8:
                this.bsMark(11);
                this.state = 9;
                break;
            case 9:
                this.setArgs();
                this.state = 10;
                return 25;

            case 10:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(11);
                this.state = 11;
                continue;

            case 11:
                this.bsMark(14);
                this.state = 12;
                break;
            case 12:
                this.setArgs();
                this.state = 13;
                return 42;

            case 13:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 14:
                this.bsMark(26);
                this.state = 15;
                break;
            case 15:
                this.setArgs();
                this.state = 16;
                return 44;

            case 16:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 17;
                return 3;

            case 17:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(25);
                this.state = 18;
                break;
            case 18:
                if (!(this.lookahead(76))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 19;
                return 43;

            case 19:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 20;
                return 45;

            case 20:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 21;
                return 54;

            case 21:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(24);
                this.state = 22;
                break;
            case 22:
                this.setArgs();
                this.state = 23;
                return 44;

            case 23:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(24);
                this.state = 24;
                continue;

            case 24:
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 25:
                this.bsCommit(26);
                this.state = 26;
                continue;

            case 26:
                this.bsCommit(27);
                this.state = 27;
                continue;

            case 27:
                return -1;

            }
        }
    }

    private int _23_signature() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.OPEN_PAREN);
                if (!(this.literal("("))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                this.setArgs();
                this.state = 2;
                return 41;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(19);
                this.state = 3;
                break;
            case 3:
                this.bsMark(11);
                this.state = 4;
                break;
            case 4:
                this.setArgs();
                this.state = 5;
                return 24;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 6;
                return 41;

            case 6:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(10);
                this.state = 7;
                break;
            case 7:
                this.startToken(CroTemplateTokenTypes.COMMA);
                if (!(this.literal(","))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 8;
                return -3;

            case 8:
                this.setArgs();
                this.state = 9;
                return 41;

            case 9:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 10:
                rep = this.peekRep(11);
                ++rep;
                this.bsCommit(11);
                this.bsMark(11, rep);
                this.state = 4;
                continue;

            case 11:
                this.setArgs();
                this.state = 12;
                return 41;

            case 12:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(18);
                this.state = 13;
                break;
            case 13:
                this.startToken(CroTemplateTokenTypes.CLOSE_PAREN);
                if (!(this.literal(")"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 14;
                return -3;

            case 14:
                this.bsMark(17);
                this.state = 15;
                break;
            case 15:
                this.setArgs();
                this.state = 16;
                return 42;

            case 16:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(17);
                this.state = 17;
                continue;

            case 17:
                this.bsCommit(18);
                this.state = 18;
                continue;

            case 18:
                this.bsCommit(19);
                this.state = 19;
                continue;

            case 19:
                return -1;

            }
        }
    }

    private int _24_parameter() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.VARIABLE_NAME);
                if (!(this.literal("$"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsMark(3);
                this.state = 1;
                break;
            case 1:
                this.setArgs();
                this.state = 2;
                return 55;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(3);
                this.state = 3;
                continue;

            case 3:
                this.state = 4;
                return -3;

            case 4:
                return -1;

            }
        }
    }

    private int _25_arglist() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.OPEN_PAREN);
                if (!(this.literal("("))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                this.setArgs();
                this.state = 2;
                return 41;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(19);
                this.state = 3;
                break;
            case 3:
                this.bsMark(11);
                this.state = 4;
                break;
            case 4:
                this.setArgs();
                this.state = 5;
                return 26;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 6;
                return 41;

            case 6:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(10);
                this.state = 7;
                break;
            case 7:
                this.startToken(CroTemplateTokenTypes.COMMA);
                if (!(this.literal(","))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 8;
                return -3;

            case 8:
                this.setArgs();
                this.state = 9;
                return 41;

            case 9:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 10:
                rep = this.peekRep(11);
                ++rep;
                this.bsCommit(11);
                this.bsMark(11, rep);
                this.state = 4;
                continue;

            case 11:
                this.setArgs();
                this.state = 12;
                return 41;

            case 12:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(18);
                this.state = 13;
                break;
            case 13:
                this.startToken(CroTemplateTokenTypes.CLOSE_PAREN);
                if (!(this.literal(")"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 14;
                return -3;

            case 14:
                this.bsMark(17);
                this.state = 15;
                break;
            case 15:
                this.setArgs();
                this.state = 16;
                return 42;

            case 16:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(17);
                this.state = 17;
                continue;

            case 17:
                this.bsCommit(18);
                this.state = 18;
                continue;

            case 18:
                this.bsCommit(19);
                this.state = 19;
                continue;

            case 19:
                return -1;

            }
        }
    }

    private int _26_expression() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.declareDynamicVariable("$*LEFTSIGIL", "");
                this.declareDynamicVariable("$*PREC", "");
                this.declareDynamicVariable("$*SUB_PREC", "");
                this.declareDynamicVariable("$*ASSOC", "");
                this.declareDynamicVariable("$*NEXT_TERM", "");
                this.declareDynamicVariable("$*FAKE", 0);
                this.setArgs();
                this.state = 1;
                return 30;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(12);
                this.state = 2;
                break;
            case 2:
                if (!(this.lookahead(77))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 3;
                return 41;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.assignDynamicVariable("$*NEXT_TERM", "");
                this.setArgs();
                this.state = 4;
                return 28;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.precInfoToken();
                this.state = 5;
                return -3;

            case 5:
                this.bsMark(8);
                this.state = 6;
                break;
            case 6:
                if (this.lookahead(78)) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 7;
                return 41;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 8:
                this.bsMark(11);
                this.state = 9;
                break;
            case 9:
                this.setArgs();
                this.state = 10;
                return 27;

            case 10:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(11);
                this.state = 11;
                continue;

            case 11:
                rep = this.peekRep(12);
                ++rep;
                this.bsCommit(12);
                this.bsMark(12, rep);
                this.state = 2;
                continue;

            case 12:
                this.bsFailMark(15);
                this.bsMark(14);
                if (this.lookahead(79)) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.startToken(CroTemplateTokenTypes.END_OF_EXPR);
                this.state = 13;
                return -3;

            case 13:
                this.bsCommit(15);
                this.state = 15;
                continue;

            case 14:
                this.state = 15;
                continue;

            case 15:
                return -1;

            }
        }
    }

    private int _27_nextterm() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.setArgs();
                this.state = 1;
                return 30;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _28_infixish() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.setArgs();
                this.state = 1;
                return 29;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _29_infix() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.INFIX);
                this.bsFailMark(25);
                this.bsMark(1);
                if (!(this.literal("!==="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "m=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 1:
                this.bsMark(2);
                if (!(this.literal("==="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "m=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 2:
                this.bsMark(3);
                if (!(this.literal("and"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "d=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 3:
                this.bsMark(4);
                if (!(this.literal("=="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "m=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 4:
                this.bsMark(5);
                if (!(this.literal("!="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "m=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 5:
                this.bsMark(6);
                if (!(this.literal("<="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "m=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 6:
                this.bsMark(7);
                if (!(this.literal(">="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "m=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 7:
                this.bsMark(8);
                if (!(this.literal("eq"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "m=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 8:
                this.bsMark(9);
                if (!(this.literal("ne"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "m=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 9:
                this.bsMark(10);
                if (!(this.literal("le"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "m=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 10:
                this.bsMark(11);
                if (!(this.literal("ge"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "m=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 11:
                this.bsMark(12);
                if (!(this.literal("lt"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "m=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 12:
                this.bsMark(13);
                if (!(this.literal("gt"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "m=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 13:
                this.bsMark(14);
                if (!(this.literal("&&"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "l=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 14:
                this.bsMark(15);
                if (!(this.literal("||"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "k=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 15:
                this.bsMark(16);
                if (!(this.literal("or"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "c=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 16:
                this.bsMark(17);
                if (!(this.literal("*"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "u=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 17:
                this.bsMark(18);
                if (!(this.literal("/"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "u=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 18:
                this.bsMark(19);
                if (!(this.literal("%"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "u=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 19:
                this.bsMark(20);
                if (!(this.literal("+"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "t=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 20:
                this.bsMark(21);
                if (!(this.literal("-"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "t=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 21:
                this.bsMark(22);
                if (!(this.literal("x"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "s=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 22:
                this.bsMark(23);
                if (!(this.literal("~"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "r=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 23:
                this.bsMark(24);
                if (!(this.literal("<"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "m=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.bsCommit(25);
                this.state = 25;
                continue;

            case 24:
                if (!(this.literal(">"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.assignDynamicVariable("$*PREC", "m=");
                this.assignDynamicVariable("$*ASSOC", "left");
                this.state = 25;
                continue;

            case 25:
                this.state = 26;
                return -3;

            case 26:
                return -1;

            }
        }
    }

    private int _30_term() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.bsFailMark(14);
                this.bsMark(2);
                this.setArgs();
                this.state = 1;
                return 31;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 2:
                this.bsMark(4);
                this.setArgs();
                this.state = 3;
                return 34;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 4:
                this.bsMark(6);
                this.setArgs();
                this.state = 5;
                return 33;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 6:
                this.bsMark(8);
                this.setArgs();
                this.state = 7;
                return 32;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 8:
                this.bsMark(10);
                this.setArgs();
                this.state = 9;
                return 35;

            case 9:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 10:
                this.bsMark(12);
                this.setArgs();
                this.state = 11;
                return 36;

            case 11:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 12:
                this.setArgs();
                this.state = 13;
                return 37;

            case 13:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 14;
                continue;

            case 14:
                return -1;

            }
        }
    }

    private int _31_single_quote_string() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.STRING_QUOTE_SINGLE);
                if (!(this.literal("'"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                this.bsMark(6);
                this.state = 2;
                break;
            case 2:
                this.startToken(CroTemplateTokenTypes.STRING_TEXT);
                this.bsFailMark(4);
                this.state = 3;
                break;
            case 3:
                if (!(this.notInCharList("'"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(4);
                ++rep;
                this.bsCommit(4);
                this.bsMark(4, rep);
                this.state = 3;
                continue;

            case 4:
                this.state = 5;
                return -3;

            case 5:
                this.bsCommit(6);
                this.state = 6;
                continue;

            case 6:
                this.bsMark(9);
                this.state = 7;
                break;
            case 7:
                this.startToken(CroTemplateTokenTypes.STRING_QUOTE_SINGLE);
                if (!(this.literal("'"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 8;
                return -3;

            case 8:
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 9:
                return -1;

            }
        }
    }

    private int _32_int() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.INT_LITERAL);
                this.bsMark(2);
                this.state = 1;
                break;
            case 1:
                if (!(this.literal("-"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(2);
                this.state = 2;
                continue;

            case 2:
                this.bsFailMark(4);
                this.state = 3;
                break;
            case 3:
                if (!(this.digitChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(4);
                ++rep;
                this.bsCommit(4);
                this.bsMark(4, rep);
                this.state = 3;
                continue;

            case 4:
                this.state = 5;
                return -3;

            case 5:
                return -1;

            }
        }
    }

    private int _33_rat() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.RAT_LITERAL);
                this.bsMark(2);
                this.state = 1;
                break;
            case 1:
                if (!(this.literal("-"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(2);
                this.state = 2;
                continue;

            case 2:
                this.bsMark(4);
                this.state = 3;
                break;
            case 3:
                if (!(this.digitChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(4);
                ++rep;
                this.bsCommit(4);
                this.bsMark(4, rep);
                this.state = 3;
                continue;

            case 4:
                if (!(this.literal("."))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsFailMark(6);
                this.state = 5;
                break;
            case 5:
                if (!(this.digitChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(6);
                ++rep;
                this.bsCommit(6);
                this.bsMark(6, rep);
                this.state = 5;
                continue;

            case 6:
                this.state = 7;
                return -3;

            case 7:
                return -1;

            }
        }
    }

    private int _34_num() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.NUM_LITERAL);
                this.bsMark(2);
                this.state = 1;
                break;
            case 1:
                if (!(this.literal("-"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(2);
                this.state = 2;
                continue;

            case 2:
                this.bsMark(4);
                this.state = 3;
                break;
            case 3:
                if (!(this.digitChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(4);
                ++rep;
                this.bsCommit(4);
                this.bsMark(4, rep);
                this.state = 3;
                continue;

            case 4:
                if (!(this.literal("."))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsFailMark(6);
                this.state = 5;
                break;
            case 5:
                if (!(this.digitChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(6);
                ++rep;
                this.bsCommit(6);
                this.bsMark(6, rep);
                this.state = 5;
                continue;

            case 6:
                if (!(this.inCharList("eE"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsMark(8);
                this.state = 7;
                break;
            case 7:
                if (!(this.literal("-"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 8:
                this.bsFailMark(10);
                this.state = 9;
                break;
            case 9:
                if (!(this.digitChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(10);
                ++rep;
                this.bsCommit(10);
                this.bsMark(10, rep);
                this.state = 9;
                continue;

            case 10:
                this.state = 11;
                return -3;

            case 11:
                return -1;

            }
        }
    }

    private int _35_variable() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.VARIABLE_NAME);
                if (!(this.literal("$"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsMark(3);
                this.state = 1;
                break;
            case 1:
                this.setArgs();
                this.state = 2;
                return 55;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(3);
                this.state = 3;
                continue;

            case 3:
                this.state = 4;
                return -3;

            case 4:
                this.bsMark(10);
                this.state = 5;
                break;
            case 5:
                this.setArgs();
                this.state = 6;
                return 46;

            case 6:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(9);
                this.state = 7;
                break;
            case 7:
                this.setArgs();
                this.state = 8;
                return 38;

            case 8:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 9:
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 10:
                return -1;

            }
        }
    }

    private int _36_deref_term() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.setArgs();
                this.state = 1;
                return 46;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(4);
                this.state = 2;
                break;
            case 2:
                this.setArgs();
                this.state = 3;
                return 38;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(4);
                this.state = 4;
                continue;

            case 4:
                return -1;

            }
        }
    }

    private int _37_parenthesized_expression() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.OPEN_PAREN);
                if (!(this.literal("("))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                this.setArgs();
                this.state = 2;
                return 41;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(9);
                this.state = 3;
                break;
            case 3:
                this.setArgs();
                this.state = 4;
                return 26;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 5;
                return 41;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(8);
                this.state = 6;
                break;
            case 6:
                this.startToken(CroTemplateTokenTypes.CLOSE_PAREN);
                if (!(this.literal(")"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 7;
                return -3;

            case 7:
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 8:
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 9:
                return -1;

            }
        }
    }

    private int _38_deref() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.setArgs();
                this.state = 1;
                return 39;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(7);
                this.state = 2;
                break;
            case 2:
                this.setArgs();
                this.state = 3;
                return 46;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(6);
                this.state = 4;
                break;
            case 4:
                this.setArgs();
                this.state = 5;
                return 39;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(6);
                this.state = 6;
                continue;

            case 6:
                rep = this.peekRep(7);
                ++rep;
                this.bsCommit(7);
                this.bsMark(7, rep);
                this.state = 2;
                continue;

            case 7:
                return -1;

            }
        }
    }

    private int _39_deref_item() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.setArgs();
                this.state = 1;
                return 40;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _40_deref_item_smart() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.IDENTIFER);
                this.setArgs();
                this.state = 1;
                return 55;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 2;
                return -3;

            case 2:
                return -1;

            }
        }
    }

    private int _41_ws() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.bsFailMark(5);
                this.bsMark(4);
                this.startToken(CroTemplateTokenTypes.SYNTAX_WHITE_SPACE);
                this.bsFailMark(2);
                this.state = 1;
                break;
            case 1:
                if (!(this.spaceChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(2);
                ++rep;
                this.bsCommit(2);
                this.bsMark(2, rep);
                this.state = 1;
                continue;

            case 2:
                this.state = 3;
                return -3;

            case 3:
                this.bsCommit(5);
                this.state = 5;
                continue;

            case 4:
                this.state = 5;
                continue;

            case 5:
                return -1;

            }
        }
    }

    private int _42_hws() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.SYNTAX_WHITE_SPACE);
                this.bsFailMark(2);
                this.state = 1;
                break;
            case 1:
                if (!(this.inCharList("\u0009 \u00A0\u1680\u180E\u2002\u2003\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(2);
                ++rep;
                this.bsCommit(2);
                this.bsMark(2, rep);
                this.state = 1;
                continue;

            case 2:
                this.state = 3;
                return -3;

            case 3:
                return -1;

            }
        }
    }

    private int _43_tlt() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.TEMPLATE_TAG_OPEN);
                if (!(this.literal("<"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                return -1;

            }
        }
    }

    private int _44_tgt() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.TEMPLATE_TAG_CLOSE);
                if (!(this.literal(">"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                return -1;

            }
        }
    }

    private int _45_tclose() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.TEMPLATE_TAG_SLASH);
                if (!(this.literal("/"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                return -1;

            }
        }
    }

    private int _46_dot() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.DOT);
                if (!(this.literal("."))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                return -1;

            }
        }
    }

    private int _47_lt() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.LITERAL_TAG_OPEN);
                if (!(this.literal("<"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                return -1;

            }
        }
    }

    private int _48_gt() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.LITERAL_TAG_CLOSE);
                if (!(this.literal(">"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                return -1;

            }
        }
    }

    private int _49_close() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.LITERAL_TAG_SLASH);
                if (!(this.literal("/"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                return -1;

            }
        }
    }

    private int _50_iter_sigil() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.TEMPLATE_TAG_ITER_SIGIL);
                if (!(this.literal("@"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                return -1;

            }
        }
    }

    private int _51_cond_sigil() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.TEMPLATE_TAG_COND_SIGIL);
                if (!(this.inCharList("?!"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                return -1;

            }
        }
    }

    private int _52_call_sigil() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.TEMPLATE_TAG_CALL_SIGIL);
                if (!(this.literal("&"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                return -1;

            }
        }
    }

    private int _53_decl_sigil() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.TEMPLATE_TAG_DECL_SIGIL);
                if (!(this.literal(":"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                return -1;

            }
        }
    }

    private int _54_apply_sigil() {
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.startToken(CroTemplateTokenTypes.TEMPLATE_TAG_APPLY_SIGIL);
                if (!(this.literal("|"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                return -1;

            }
        }
    }

    private int _55_identifier() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.setArgs();
                this.state = 1;
                return 56;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(4);
                this.state = 2;
                break;
            case 2:
                if (!(this.inCharList("-'"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.setArgs();
                this.state = 3;
                return 56;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                rep = this.peekRep(4);
                ++rep;
                this.bsCommit(4);
                this.bsMark(4, rep);
                this.state = 2;
                continue;

            case 4:
                return -1;

            }
        }
    }

    private int _56_ident() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.checkArgs(0);
                this.bsFailMark(2);
                this.bsMark(1);
                if (!(this.alphaChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(2);
                this.state = 2;
                continue;

            case 1:
                if (!(this.literal("_"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 2;
                continue;

            case 2:
                this.bsMark(4);
                this.state = 3;
                break;
            case 3:
                if (!(this.wordChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(4);
                ++rep;
                this.bsCommit(4);
                this.bsMark(4, rep);
                this.state = 3;
                continue;

            case 4:
                return -1;

            }
        }
    }

    private int ___lookahead_0() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("<"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                if (this.lookahead(58)) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                if (this.lookahead(59)) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_1() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.inCharList("/"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_2() {
        while (true) {
            switch (this.state) {
            case 0:
                this.setArgs();
                this.state = 1;
                return 10;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int ___lookahead_3() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("</"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                if (this.lookahead(61)) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_4() {
        while (true) {
            switch (this.state) {
            case 0:
                this.setArgs();
                this.state = 1;
                return 10;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int ___lookahead_5() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("<."))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_6() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("<$"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_7() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("<@"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_8() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("</@"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_9() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("<"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                if (!(this.inCharList("?!"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_10() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("</"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                if (!(this.inCharList("?!"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_11() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("<&"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_12() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("<:sub"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_13() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("</:"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_14() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("<:macro"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_15() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("</:"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_16() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("<:body"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_17() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("<:use"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_18() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("<|"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_19() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("</|"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_20() {
        while (true) {
            switch (this.state) {
            case 0:
                this.setArgs();
                this.state = 1;
                return 41;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 2;
                return 28;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.setArgs();
                this.state = 3;
                return 41;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int ___lookahead_21() {
        while (true) {
            switch (this.state) {
            case 0:
                this.setArgs();
                this.state = 1;
                return 41;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                if (!(this.inCharList("]})"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_22() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.endOfString())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    public MAINBraid createInstance() {
        return new MAINBraid();
    }

}
