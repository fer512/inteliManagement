import { Injectable, Component, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'profile-img',
    templateUrl: './profile-img.component.html'
})
export class ProfileImg {
    @Input() img: string;

    imageToShow: any;

    isImageLoading: boolean;

    private imgUrl = SERVER_API_URL + 'api/file/download';

    constructor(private http: HttpClient) {}

    createImageFromBlob(image: Blob) {
        let reader = new FileReader();
        reader.addEventListener(
            'load',
            () => {
                this.imageToShow = reader.result;
            },
            false
        );

        if (image) {
            reader.readAsDataURL(image);
        }
    }

    getImg() {
        this.isImageLoading = true;
        this.getImgService(this.img).subscribe(
            data => {
                this.createImageFromBlob(data);
                this.isImageLoading = false;
            },
            error => {
                this.isImageLoading = false;
                console.log(error);
            }
        );
    }

    getImgService(name: string): Observable<any> {
        return this.http.get(`${this.imgUrl}/${name}`, { responseType: 'blob' });
    }
}
