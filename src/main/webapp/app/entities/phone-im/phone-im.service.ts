import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPhoneIm } from 'app/shared/model/phone-im.model';

type EntityResponseType = HttpResponse<IPhoneIm>;
type EntityArrayResponseType = HttpResponse<IPhoneIm[]>;

@Injectable({ providedIn: 'root' })
export class PhoneImService {
    private resourceUrl = SERVER_API_URL + 'api/phones';

    constructor(private http: HttpClient) {}

    create(phone: IPhoneIm): Observable<EntityResponseType> {
        return this.http.post<IPhoneIm>(this.resourceUrl, phone, { observe: 'response' });
    }

    update(phone: IPhoneIm): Observable<EntityResponseType> {
        return this.http.put<IPhoneIm>(this.resourceUrl, phone, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPhoneIm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPhoneIm[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
