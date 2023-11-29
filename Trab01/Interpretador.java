

class Interpretador {

    int acumulador = 0;

    Integer geraCodigo(ArvoreSintatica arv) {
        return geraCodigo2(arv);
    }

    Integer geraCodigo2(ArvoreSintatica arv) {
        if (arv instanceof Mult) {
            int registerOne = geraCodigo2(((Mult) arv).arg1);
            int registerTwo = geraCodigo2(((Mult) arv).arg2);
            return registerOne * registerTwo;
        }

        if (arv instanceof Soma) {
            int registerOne = geraCodigo2(((Soma) arv).arg1);
            int registerTwo = geraCodigo2(((Soma) arv).arg2);
            return registerOne + registerTwo;
        }

        if (arv instanceof Sub) {
            int registerOne = geraCodigo2(((Sub) arv).arg1);
            int registerTwo = geraCodigo2(((Sub) arv).arg2);
            return registerOne - registerTwo;
        }

        if (arv instanceof Div) {
            int registerOne = geraCodigo2(((Div) arv).arg1);
            int registerTwo = geraCodigo2(((Div) arv).arg2);
            return registerOne / registerTwo;
        }

        if (arv instanceof Num) {
            return (((Num) arv).num);
        }

        return 0;
    }
}
