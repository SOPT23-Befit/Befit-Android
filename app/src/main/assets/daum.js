<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<script>

  window.addEventListener("message", onReceivedPostMessage, false);

  function onReceivedPostMessage(event){
    //..ex deconstruct event into action & params
    var action = event.data.action;
    var params = event.data.params;
    console.log("onReceivedPostMessage "+event);
  };

  function onReceivedActivityMessageViaJavascriptInterface(json){
    //..ex deconstruct data into action & params
    var data = JSON.parse(json);
    var action = data.action;
    var params = data.params;
    console.log("onReceivedActivityMessageViaJavascriptInterface "+event);
  };

  function postMessageToiOS(postData) {
      window.webkit.messageHandlers.callBackHandler.postMessage(postData);
  };

  var element_layer = document.getElementById('layer');

  function execDaumPostcode() {
    new daum.Postcode({
      oncomplete: function(data) {

        var fullAddr = data.address; // 최종 주소 변수
        var extraAddr = ''; // 조합형 주소 변수

        if(data.addressType === 'R'){

          if(data.bname !== ''){
            extraAddr += data.bname;
          }

          if(data.buildingName !== ''){
            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
          }

          fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
        }

        var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
        var extraRoadAddr = ''; // 도로명 조합형 주소 변수

        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
          extraRoadAddr += data.bname;
        }

        if(data.buildingName !== '' && data.apartment === 'Y'){
          extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
        }

        if(extraRoadAddr !== ''){
          extraRoadAddr = ' (' + extraRoadAddr + ')';
        }

        if(fullRoadAddr !== ''){
          fullRoadAddr += extraRoadAddr;
        }

        var postData = {
            postcode : data.postcode,
            zonecode : data.zonecode,
            addr : fullRoadAddr
        };

        window.TestApp.setAddress(data.zonecode , data.postcode, data.fullAddr);
      },
      width : '100%',
      height : '100%'
    }).embed(element_layer);
    element_layer.style.display = 'block';
    initLayerPosition();
  }

  function initLayerPosition(){
    var width = (window.innerWidth || document.documentElement.clientWidth); //우편번호서비스가 들어갈 element의 width
    var height = (window.innerHeight || document.documentElement.clientHeight); //우편번호서비스가 들어갈 element의 height
    var borderWidth = 5; //샘플에서 사용하는 border의 두께
    element_layer.style.width = width + 'px';
    element_layer.style.height = height + 'px';
    element_layer.style.border = borderWidth + 'px solid';
    element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
    element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
  };

</script>