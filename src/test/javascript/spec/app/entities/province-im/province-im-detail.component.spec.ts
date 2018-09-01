/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { ProvinceImDetailComponent } from 'app/entities/province-im/province-im-detail.component';
import { ProvinceIm } from 'app/shared/model/province-im.model';

describe('Component Tests', () => {
    describe('ProvinceIm Management Detail Component', () => {
        let comp: ProvinceImDetailComponent;
        let fixture: ComponentFixture<ProvinceImDetailComponent>;
        const route = ({ data: of({ province: new ProvinceIm(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ProvinceImDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProvinceImDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProvinceImDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.province).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
