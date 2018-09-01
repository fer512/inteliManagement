import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAddressIm } from 'app/shared/model/address-im.model';

type EntityResponseType = HttpResponse<IAddressIm>;
type EntityArrayResponseType = HttpResponse<IAddressIm[]>;

@Injectable({ providedIn: 'root' })
export class AddressImService {
    private resourceUrl = SERVER_API_URL + 'api/addresses';

    constructor(private http: HttpClient) {}

    create(address: IAddressIm): Observable<EntityResponseType> {
        return this.http.post<IAddressIm>(this.resourceUrl, address, { observe: 'response' });
    }

    update(address: IAddressIm): Observable<EntityResponseType> {
        return this.http.put<IAddressIm>(this.resourceUrl, address, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAddressIm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAddressIm[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
