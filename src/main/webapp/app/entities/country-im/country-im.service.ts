import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICountryIm } from 'app/shared/model/country-im.model';

type EntityResponseType = HttpResponse<ICountryIm>;
type EntityArrayResponseType = HttpResponse<ICountryIm[]>;

@Injectable({ providedIn: 'root' })
export class CountryImService {
    private resourceUrl = SERVER_API_URL + 'api/countries';

    constructor(private http: HttpClient) {}

    create(country: ICountryIm): Observable<EntityResponseType> {
        return this.http.post<ICountryIm>(this.resourceUrl, country, { observe: 'response' });
    }

    update(country: ICountryIm): Observable<EntityResponseType> {
        return this.http.put<ICountryIm>(this.resourceUrl, country, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICountryIm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICountryIm[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
