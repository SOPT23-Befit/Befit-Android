

<script src="https://trilliwon.github.io/postcode/"></script>

<script>
    new daum.Postcode({
        oncomplete: function(data) {
                Log.v("oncomplete","bbbbb")
            if(data.userSelectedType=="R"){
                    Log.v("oncomplete",userSelectedType)

                window.TestApp.setAddress(data.zonecode, data.roadAddress, data.buildingName);
            }
            else{
                    Log.v("oncomplete",userSelectedType)
                window.TestApp.setAddress(data.zonecode, data.jibunAddress, data.buildingName);
            }
        }
    }).open();

</script>