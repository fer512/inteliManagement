import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProvinceIm } from 'app/shared/model/province-im.model';

type EntityResponseType = HttpResponse<IProvinceIm>;
type EntityArrayResponseType = HttpResponse<IProvinceIm[]>;

@Injectable({ providedIn: 'root' })
export class ProvinceImService {
    private resourceUrl = SERVER_API_URL + 'api/provinces';

    constructor(private http: HttpClient) {}

    create(province: IProvinceIm): Observable<EntityResponseType> {
        return this.http.post<IProvinceIm>(this.resourceUrl, province, { observe: 'response' });
    }

    update(province: IProvinceIm): Observable<EntityResponseType> {
        return this.http.put<IProvinceIm>(this.resourceUrl, province, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProvinceIm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProvinceIm[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
