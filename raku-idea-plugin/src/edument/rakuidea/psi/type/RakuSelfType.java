package edument.rakuidea.psi.type;

import edument.rakuidea.psi.RakuPackageDecl;

public class RakuSelfType extends RakuResolvedType {
    public RakuSelfType(RakuPackageDecl packageDecl) {
        super(packageDecl.getPackageName(), packageDecl);
    }
}
