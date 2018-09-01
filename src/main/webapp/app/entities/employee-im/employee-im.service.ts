import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEmployeeIm } from 'app/shared/model/employee-im.model';

type EntityResponseType = HttpResponse<IEmployeeIm>;
type EntityArrayResponseType = HttpResponse<IEmployeeIm[]>;

@Injectable({ providedIn: 'root' })
export class EmployeeImService {
    private resourceUrl = SERVER_API_URL + 'api/employees';

    constructor(private http: HttpClient) {}

    create(employee: IEmployeeIm): Observable<EntityResponseType> {
        return this.http.post<IEmployeeIm>(this.resourceUrl, employee, { observe: 'response' });
    }

    update(employee: IEmployeeIm): Observable<EntityResponseType> {
        return this.http.put<IEmployeeIm>(this.resourceUrl, employee, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEmployeeIm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEmployeeIm[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
