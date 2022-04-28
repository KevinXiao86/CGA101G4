// 頁面加載事件, 等頁面渲染完畢在執行 js
window.addEventListener("load", () => {
  // 先獲取一下上傳的這個圖標元素
  let uploadFile = document.querySelector("#file");

  // 在獲取下一個整個 lsit 和 file 這個框框
  let list = document.querySelector(".list");
  let file = document.querySelector(".file");

  // 為這個按鈕添加 change 事件, 當你選擇圖片然後點擊確定時觸發
  uploadFile.addEventListener("change", function () {
    for (let item of this.files) {
      // 利用正則表達式判斷是否為圖片類型
      if (!/image\/\w+/.test(item.type)) {
        alert("只能選擇圖片");
        return;
      }
      // 創建一個 FileReader() 對象
      let reader = new FileReader();
      // readAsDataURL: 可以將上傳的圖片格式轉為 BASE64, 然後存入到圖片路徑
      // 這樣就可以上傳任意位置的圖片
      reader.readAsDataURL(item);
      reader.addEventListener("load", function () {
        // reader.result 得到的是轉換後的圖片 base64 格式直接放到 src 路徑即可
        let li = document.createElement("li");
        li.innerHTML = `
				<div class="close">×</div>
				<img src="
					${this.result}
					" />
				`;
        list.insertBefore(li, file);
      });
    }
  });
  
  
  
  // 現在做刪除功能, 因為每次添加新的 li 元素都會重新繪製 dom 樹
  // 沒辦法給每個 .close 元素綁定事件, 這裡我們就用事件委託實現
  list.addEventListener("click", (e) => {
    // e 是頁面一開啟就自動生成的事件對象
    // e.target 是觸發事件的對象 , nodeName 是每一個 dom 元素都有節點, 且值全部都是大寫
    // 這裡就是如果點擊的是 x 號就會刪除, 否則不會刪除
    if (e.target.nodeName == "DIV") {
      // removeChild 是移除子元素, 括號寫的是點擊的 div 對應的父元素就是某一個 li 了
      list.removeChild(e.target.parentNode);
    }
  });
});