
let roomCount = 0;

// 각 객실에 대한 파일 배열을 관리하기 위한 객체
let roomFiles = {};
let endIndex = [];


document.getElementById('addRoomBtn').addEventListener('click', function() {
    roomCount ++;
    var roomContainer = document.createElement('div');
    roomContainer.classList.add('room-container');
    roomContainer.setAttribute('id', `room-${roomCount}`);

    roomContainer.innerHTML = `
        <div class="row mb-3">
            <h1>${roomCount}</h1>
            <input type="hidden" th:name="www">
            <label for="roomName-${roomCount}" class="col-sm-2 col-form-label">객실 이름</label>
            <input type="hidden" name="roomCount"  value="${roomCount}">
            <div class="col-sm-10">
                <input type="text" class="form-control" th:field="roomName" name="roomName" value="" required> 
            </div>
        </div>
        <table class="table table-bordered text-center">
            <thead>
                <tr>
                    <th>요금타입</th>
                    <th>주중</th>
                    <th>금요일</th>
                    <th>토요일</th>
                    <th>일요일</th>
                    <th>비고</th>
                </tr>
            </thead>
            <tbody id="rateTableBody-${roomCount}">
                <tr>
                    <td>기본금액</td>
                    <td><input type="hidden" class="form-control" th:name="wow" value="" required></td>
                    <td><input type="text" class="form-control" name="weekdayRate" value="" required></td>
                    <td><input type="text" class="form-control" name="fridayRate" value="" required></td>
                    <td><input type="text" class="form-control" name="saturdayRate" value="" required></td>
                    <td><input type="text" class="form-control" name="sundayRate" value="" required></td>
                    <td><input type="button" class="btn btn-save btn-sm">저장</input></td>
                </tr>
            </tbody>
        </table>

        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">카테고리 선택 (단일 선택)</label>
            <div class="col-sm-10 tags">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="type-${roomCount}" id="type1-${roomCount}" data-tag="오션뷰">
                    <label class="form-check-label" for="type1-${roomCount}">오션뷰</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="type-${roomCount}" id="type2-${roomCount}" data-tag="리버뷰">
                    <label class="form-check-label" for="type2-${roomCount}">리버뷰</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="type-${roomCount}" id="type3-${roomCount}" data-tag="시티뷰">
                    <label class="form-check-label" for="type3-${roomCount}">시티뷰</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="type-${roomCount}" id="type5-${roomCount}" data-tag="마운틴뷰">
                    <label class="form-check-label" for="type5-${roomCount}">마운틴뷰</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="type-${roomCount}" id="type6-${roomCount}" data-tag="others" onchange="toggleTextbox(this)">
                    <label class="form-check-label" for="type6-${roomCount}">그 외</label>
                    <input type="text" id="others-${roomCount}" disabled>
                </div>
            </div>
        </div>
        
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">기준인원</label>
            <div class="col-sm-10 d-flex align-items-center">
                <div class="btn btn-secondary decrement" data-room-id="${roomCount}" data-type="basic">-</div>
                <input type="text" class="form-control text-center mx-2 maxPeople" name="standardOccupation" id="basicPeople-${roomCount}" value="1" readonly>
                <div class="btn btn-secondary increment" data-room-id="${roomCount}" data-type="basic">+</div>
            </div>
        </div>
        <div class="row mb-3">
            <label class="col-sm-2 col-form-label">최대인원</label>
            <div class="col-sm-10 d-flex align-items-center">
                <div class="btn btn-secondary decrement" data-room-id="${roomCount}" data-type="max">-</div>
                <input type="text" class="form-control text-center mx-2 maxPeople" name="maxOccupation" id="maxPeople-${roomCount}" value="1" readonly>
                <div class="btn btn-secondary increment" data-room-id="${roomCount}" data-type="max">+</div>
            </div>
        </div>
        
        <div class="row">
                    <label class="col-sm-2 col-form-label">객실 이미지</label>
                    <div class="col-sm-10">
                        <ul class="product-gallery" id="preview-container-${roomCount}"></ul>
                        <input class="btn btn-g btn-circle image-upload" type="file" name="previewFiles" multiple id="preview-file-input-${roomCount}" data-room-id="${roomCount}">
                    </div>
                </div>
        <button class="btn btn-delete" data-room-id="${roomCount}">삭제</button>
    `;

    document.getElementById('roomsContainer').appendChild(roomContainer);
});

// 카테고리 선택 및 태그 추가
document.querySelectorAll('.tag input[type="checkbox"]').forEach(function (checkbox) {
    checkbox.addEventListener('change', function () {

        var subFacilityInput = document.getElementById('sub-facility');
        var tagValue = this.getAttribute('data-tag');

        if (this.checked) {
            if (subFacilityInput.value) {
                subFacilityInput.value += ', ' + tagValue;
            } else {
                subFacilityInput.value = tagValue;
            }
        } else {
            var values = subFacilityInput.value.split(', ');
            values = values.filter(function (value) {
                return value !== tagValue;
            });
            subFacilityInput.value = values.join(', ');
        }
    });
});

function calculateEndIndex(roomCount, roomFiles, roomId) {
    let currentTotalFiles = 0;
    let endIndex = [];

    console.log("roomCount : " + roomCount);

    for (let i = 0; i <= roomCount; i++) {
        if (roomFiles[i]) {
            console.log(`roomFiles[${i}].length before: ${roomFiles[i].length}`);
            currentTotalFiles += roomFiles[i].length;
            console.log(`currentTotalFiles after adding room ${i}: ${currentTotalFiles}`);
        }

        // endIndex 갱신
        if (roomId === i) {
            endIndex[i] = currentTotalFiles;
        } else {
            endIndex[i] = currentTotalFiles;
        }

        console.log(endIndex[i]);
    }

    return endIndex;
}


// 이미지 업로드 및 미리보기 기능 추가
document.addEventListener('change', function(event) {
    if (event.target && event.target.classList.contains('image-upload')) {
        let roomId = event.target.getAttribute('data-room-id');
        roomId = parseInt(roomId, 10);
        var imagePreview = document.getElementById('preview-container-' + roomId);
        const selectedFiles = Array.from(event.target.files);


        console.log("=============================================")
        console.log(`Room ID: ${roomId}`);
        console.log(`Selected Files:`, selectedFiles);

        // roomFiles 객체에 해당 roomId가 없으면 빈 배열로 초기화
        if (!roomFiles[roomId]) {
            roomFiles[roomId] = [];
        }


        // 이미지 파일 추가
        selectedFiles.forEach(file => {
            console.log("qwer :" + file);
            roomFiles[roomId].push(file);
        });

        console.log(`roomFiles[${roomId}]:`, roomFiles[roomId]);
        console.log(`roomFiles length: ${roomFiles[roomId].length}`);

        // endIndex 갱신
        console.log('qqqqq : ' + endIndex);
        endIndex = calculateEndIndex(roomCount, roomFiles, roomId);

        console.log('hhhh : ' + endIndex);
        console.log(`Room ${roomId} - Updated endIndex: ${endIndex[roomId]}`);
        selectedFiles.forEach((file) => {
            const reader = new FileReader();
            reader.onload = function(e) {
                const imgElement = document.createElement('img');
                imgElement.src = e.target.result;
                imgElement.classList.add('preview-image');

                const imgContainer = document.createElement('li');
                imgContainer.classList.add('preview-item');

                // 삭제 버튼 생성
                const deleteButton = document.createElement('button');
                deleteButton.textContent = 'Delete';
                deleteButton.classList.add('delete-button');
                deleteButton.addEventListener('click', function() {
                    deleteFile(roomId, file, imgContainer);
                });

                imgContainer.appendChild(imgElement);
                imgContainer.appendChild(deleteButton);
                imagePreview.appendChild(imgContainer);
            }
            reader.readAsDataURL(file);

            // 파일을 roomFiles 배열에 추가
            // roomFiles[roomId].push(file);
        });
        console.log("aaa : " + endIndex)
        // endIndex += selectedFiles.length;
    console.log("????  : " + endIndex)
        // 선택된 파일 초기화 (동일한 파일 다시 선택할 수 있게 함)
        event.target.value = '';
    }

});
// 파일 삭제 함수
function deleteFile(roomId, file, imgContainer) {
    const roomFilesArray = roomFiles[roomId];
    const fileIndex = roomFilesArray.indexOf(file);
    if (fileIndex > -1) {
        roomFilesArray.splice(fileIndex, 1); // 배열에서 파일 제거
    }
    imgContainer.remove(); // 미리보기에서 이미지 제거
}



// 이미지 업로드 및 endIndex 갱신
// document.addEventListener('change', function(event) {
//     if (event.target && event.target.classList.contains('image-upload')) {
//         const roomId = event.target.getAttribute('data-room-id');
//         const selectedFiles = Array.from(event.target.files);
//
//         // roomFiles 객체에 해당 roomId가 없으면 빈 배열로 초기화
//         if (!roomFiles[roomId]) {
//             roomFiles[roomId] = [];
//         }
//
//         // 이미지 파일 추가
//         selectedFiles.forEach(file => {
//             roomFiles[roomId].push(file);
//         });
//
//         // endIndex 갱신
//         let currentTotalFiles = 0;
//         for (let i = 1; i <= roomCount; i++) {
//             if (roomFiles[i]) {
//                 currentTotalFiles += roomFiles[i].length;
//             }
//             endIndex[i] = currentTotalFiles; // 각 객실의 현재 endIndex 저장
//         }
//
//         console.log(`Room ${roomId} - Updated endIndex: ${endIndex[roomId]}`);
//     }
// });






// 인원 증가/감소 버튼의 이벤트 리스너 추가
document.addEventListener('click', function(event) {
    if (event.target && event.target.classList.contains('decrement')) {
        var roomId = event.target.getAttribute('data-room-id');
        var type = event.target.getAttribute('data-type');
        var inputField;

        if (type === 'basic') {
            inputField = document.getElementById('basicPeople-' + roomId);
        } else if (type === 'max') {
            inputField = document.getElementById('maxPeople-' + roomId);
        }

        var currentValue = parseInt(inputField.value);
        if (currentValue > 1) {
            inputField.value = currentValue - 1;
        }
    }

    if (event.target && event.target.classList.contains('increment')) {
        var roomId = event.target.getAttribute('data-room-id');
        var type = event.target.getAttribute('data-type');
        var inputField;

        if (type === 'basic') {
            inputField = document.getElementById('basicPeople-' + roomId);
        } else if (type === 'max') {
            inputField = document.getElementById('maxPeople-' + roomId);
        }

        var currentValue = parseInt(inputField.value);
        inputField.value = currentValue + 1;
    }

    // 객실 삭제 버튼 클릭 시 해당 객실 삭제
    if (event.target && event.target.classList.contains('btn-delete')) {
        var roomId = event.target.getAttribute('data-room-id');
        var roomContainer = document.getElementById('room-' + roomId);
        roomContainer.remove();
        delete roomFiles[roomId]; // 해당 객실의 이미지 파일 배열 삭제
    }
});


document.querySelectorAll('.tag input[type="radio"]').forEach(function (radio) {
    radio.addEventListener('change', function () {
        var typeInput = document.getElementById('type');
        typeInput.value = this.getAttribute('data-tag');
    });
});

// 객실 금액 저장 / 수정 버튼
document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('#rateTableBody .btn-save').forEach(function(button) {
        button.addEventListener('click', function() {
            var row = this.closest('tr');
            var inputs = row.querySelectorAll('input');

            if (this.classList.contains('btn-save')) {
                // Save logic
                inputs.forEach(function(input) {
                    input.disabled = true;
                });
                this.textContent = '수정';
                this.classList.remove('btn-save');
                this.classList.add('btn-edit');
            } else if (this.classList.contains('btn-edit')) {
                // Edit logic
                inputs.forEach(function(input) {
                    input.disabled = false;
                });
                this.textContent = '저장';
                this.classList.remove('btn-edit');
                this.classList.add('btn-save');
            }
        });
    });
});

document.querySelector('form').addEventListener('submit', function(event) {
    if (!this.checkValidity()) {
        event.preventDefault(); // 폼 제출 중지
        alert('모든 필드를 채워주세요!');
    }
});

document.getElementById('accommodationForm').addEventListener('submit', function(event) {
    event.preventDefault(); // 기본 폼 제출 방지

    // FormData 객체 생성
    const formData = new FormData(this);

    // 객실 정보 추가
    for (let i = 1; i <= roomCount; i++) {
        // Room Name
        formData.append(`rooms[${i}].roomName`, document.querySelector(`#room-${i} input[name="roomName"]`).value);

        // Room Rates
        formData.append(`rooms[${i}].weekdayRate`, document.querySelector(`#room-${i} input[name="weekdayRate"]`).value);
        formData.append(`rooms[${i}].fridayRate`, document.querySelector(`#room-${i} input[name="fridayRate"]`).value);
        formData.append(`rooms[${i}].saturdayRate`, document.querySelector(`#room-${i} input[name="saturdayRate"]`).value);
        formData.append(`rooms[${i}].sundayRate`, document.querySelector(`#room-${i} input[name="sundayRate"]`).value);

        // Room Category
        const selectedCategory = document.querySelector(`#room-${i} input[name="type-${i}"]:checked`);
        if (selectedCategory) {
            formData.append(`rooms[${i}].category`, selectedCategory.getAttribute('data-tag'));
        }

        // Standard and Max Occupation
        formData.append(`rooms[${i}].standardOccupation`, document.getElementById(`basicPeople-${i}`).value);
        formData.append(`rooms[${i}].maxOccupation`, document.getElementById(`maxPeople-${i}`).value);



        // endIndex 객체를 JSON 문자열로 변환하여 추가
        // formData.append(`rooms[${i}].endIndex`, JSON.stringify({ [i]: endIndex[i] }));
        console.log(endIndex)
        console.log("++++++++++++++ : " + `rooms[${i}].endIndex`, endIndex[i])
        formData.append(`rooms[${i}].endIndex`, JSON.stringify(endIndex[i]));
        // formData.append(`rooms[${i}].endIndex`, document.querySelector(`#room-${i} input[name="endIndex"]`));

        // Room Images
        if (roomFiles[i]) {
            roomFiles[i].forEach(image => {
                formData.append('previewFiles', image)
            });
        }
        // formData.append(`rooms[${i}].endIndex`, endIndex);
    }
    //
    // // JSON으로 변환한 roomData를 FormData에 추가
    // formData.append('roomData', JSON.stringify(roomData));

    for(let key of formData.keys()) {
        console.log(key +  " +  "  + formData.get(key));
    }
    // AJAX 요청 생성
    fetch('/save-location', {
        method: 'POST',
        body: formData,
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
                alert('업소 등록이 완료되었습니다!');
                window.location.href = '/enroll';
            } else {
                alert('업소 등록 중 오류가 발생했습니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('업소 등록 중 오류가 발생했습니다.');
        });
});
