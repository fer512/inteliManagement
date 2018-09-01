/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { PhoneImDetailComponent } from 'app/entities/phone-im/phone-im-detail.component';
import { PhoneIm } from 'app/shared/model/phone-im.model';

describe('Component Tests', () => {
    describe('PhoneIm Management Detail Component', () => {
        let comp: PhoneImDetailComponent;
        let fixture: ComponentFixture<PhoneImDetailComponent>;
        const route = ({ data: of({ phone: new PhoneIm(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [PhoneImDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PhoneImDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PhoneImDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.phone).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
