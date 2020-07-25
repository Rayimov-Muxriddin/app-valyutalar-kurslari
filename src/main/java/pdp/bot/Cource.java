package pdp.bot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cource {
        private Integer id;
        private String Code;
        private String Ccy;
        private String CcyNm_RU;
        private String CcyNm_UZ;
        private String CcyNm_UZC;
        private String CcyNm_EN;
        private String Nominal;
        private String Rate;
        private String Diff;
        private String Date;
}
