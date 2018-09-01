import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICompanyIm } from 'app/shared/model/company-im.model';

type EntityResponseType = HttpResponse<ICompanyIm>;
type EntityArrayResponseType = HttpResponse<ICompanyIm[]>;

@Injectable({ providedIn: 'root' })
export class CompanyImService {
    private resourceUrl = SERVER_API_URL + 'api/companies';

    constructor(private http: HttpClient) {}

    create(company: ICompanyIm): Observable<EntityResponseType> {
        return this.http.post<ICompanyIm>(this.resourceUrl, company, { observe: 'response' });
    }

    update(company: ICompanyIm): Observable<EntityResponseType> {
        return this.http.put<ICompanyIm>(this.resourceUrl, company, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICompanyIm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICompanyIm[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
