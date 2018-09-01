/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { EmployeeImDetailComponent } from 'app/entities/employee-im/employee-im-detail.component';
import { EmployeeIm } from 'app/shared/model/employee-im.model';

describe('Component Tests', () => {
    describe('EmployeeIm Management Detail Component', () => {
        let comp: EmployeeImDetailComponent;
        let fixture: ComponentFixture<EmployeeImDetailComponent>;
        const route = ({ data: of({ employee: new EmployeeIm(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [EmployeeImDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EmployeeImDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EmployeeImDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.employee).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
