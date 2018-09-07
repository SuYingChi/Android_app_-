package com.msht.mshtlpgmaster.Bean;

public class EmpPayBean {

    /**
     * result : success
     * msg : 下单成功
     * data : {"charge":"{\"id\":\"ch_vXfDG0mbXjfT1af5G04CKWD8\",\"object\":\"charge\",\"created\":1534056916,\"livemode\":false,\"paid\":false,\"refunded\":false,\"app\":\"app_m9mbr94KurPC58Sq\",\"channel\":\"alipay\",\"orderNo\":\"000000005302\",\"clientIp\":\"120.25.195.173\",\"amount\":3960000,\"amountSettle\":3960000,\"currency\":\"cny\",\"subject\":\"LPG充值\",\"body\":\"LPG充值-民生宝\",\"timeExpire\":1534143316,\"refunds\":{\"object\":\"list\",\"url\":\"/v1/charges/ch_vXfDG0mbXjfT1af5G04CKWD8/refunds\",\"hasMore\":false},\"amountRefunded\":0,\"credential\":{\"object\":\"credential\",\"alipay\":{\"orderInfo\":\"_input_charset=\\\"utf-8\\\"&body=\\\"LPG充值-民生宝\\\"&it_b_pay=\\\"2018-08-13 14:55:16\\\"&notify_url=\\\"https%3A%2F%2Fnotify.pingxx.com%2Fnotify%2Fcharges%2Fch_vXfDG0mbXjfT1af5G04CKWD8\\\"&out_trade_no=\\\"000000005302\\\"&partner=\\\"2008842353179884\\\"&payment_type=\\\"1\\\"&seller_id=\\\"2008842353179884\\\"&service=\\\"mobile.securitypay.pay\\\"&subject=\\\"LPG充值\\\"&total_fee=\\\"39600.00\\\"&sign=\\\"NEttVENHOG16SDA0ckwwYVRHZjUwbTk0\\\"&sign_type=\\\"RSA\\\"\"}},\"description\":\"340\"}","payInfoId":"5302","chargeId":"ch_vXfDG0mbXjfT1af5G04CKWD8"}
     * msgCode : 0000
     * orderCode : 2018081011087736c99e08e24e34a3
     */

    private String result;
    private String msg;
    private DataBean data;
    private String msgCode;
    private String orderCode;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public static class DataBean {
        /**
         * charge : {"id":"ch_vXfDG0mbXjfT1af5G04CKWD8","object":"charge","created":1534056916,"livemode":false,"paid":false,"refunded":false,"app":"app_m9mbr94KurPC58Sq","channel":"alipay","orderNo":"000000005302","clientIp":"120.25.195.173","amount":3960000,"amountSettle":3960000,"currency":"cny","subject":"LPG充值","body":"LPG充值-民生宝","timeExpire":1534143316,"refunds":{"object":"list","url":"/v1/charges/ch_vXfDG0mbXjfT1af5G04CKWD8/refunds","hasMore":false},"amountRefunded":0,"credential":{"object":"credential","alipay":{"orderInfo":"_input_charset=\"utf-8\"&body=\"LPG充值-民生宝\"&it_b_pay=\"2018-08-13 14:55:16\"&notify_url=\"https%3A%2F%2Fnotify.pingxx.com%2Fnotify%2Fcharges%2Fch_vXfDG0mbXjfT1af5G04CKWD8\"&out_trade_no=\"000000005302\"&partner=\"2008842353179884\"&payment_type=\"1\"&seller_id=\"2008842353179884\"&service=\"mobile.securitypay.pay\"&subject=\"LPG充值\"&total_fee=\"39600.00\"&sign=\"NEttVENHOG16SDA0ckwwYVRHZjUwbTk0\"&sign_type=\"RSA\""}},"description":"340"}
         * payInfoId : 5302
         * chargeId : ch_vXfDG0mbXjfT1af5G04CKWD8
         */

        private String charge;
        private String payInfoId;
        private String chargeId;

        public String getCharge() {
            return charge;
        }

        public void setCharge(String charge) {
            this.charge = charge;
        }

        public String getPayInfoId() {
            return payInfoId;
        }

        public void setPayInfoId(String payInfoId) {
            this.payInfoId = payInfoId;
        }

        public String getChargeId() {
            return chargeId;
        }

        public void setChargeId(String chargeId) {
            this.chargeId = chargeId;
        }
    }
}
