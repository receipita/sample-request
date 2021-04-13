package request;

public class App {
    public static void main(String[] args) {
        if(request.Config.API_SWITCH == request.Config.REQUEST_RECEIPT_API) {
            request.RequestReceipt.callApi();
        } else if (request.Config.API_SWITCH == request.Config.GET_RESULT_RECEIPT_API) {
            request.GetResultReceipt.callApi();
        } else {
            System.out.println("Config.javaのAPI_SWITCHの適切な値(0:応募API or 1:結果取得API)を指定して下さい");
        }
    }
}
