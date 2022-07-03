package model.vo;

import java.sql.ResultSet;

public class VoConsulta {
    private ResultSet rs;

    public VoConsulta(ResultSet rs) {
        this.rs = rs;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    
}
