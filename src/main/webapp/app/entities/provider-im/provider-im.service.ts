import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProviderIm } from 'app/shared/model/provider-im.model';

type EntityResponseType = HttpResponse<IProviderIm>;
type EntityArrayResponseType = HttpResponse<IProviderIm[]>;

@Injectable({ providedIn: 'root' })
export class ProviderImService {
    private resourceUrl = SERVER_API_URL + 'api/providers';

    constructor(private http: HttpClient) {}

    create(provider: IProviderIm): Observable<EntityResponseType> {
        return this.http.post<IProviderIm>(this.resourceUrl, provider, { observe: 'response' });
    }

    update(provider: IProviderIm): Observable<EntityResponseType> {
        return this.http.put<IProviderIm>(this.resourceUrl, provider, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProviderIm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProviderIm[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
