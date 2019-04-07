var app = angular.module('app', ["ngRoute"]);
app.factory('DataService', function () {
    var factory = {};

    factory.currentQuestionSet = [];
    return factory;
});
app.config(function ($routeProvider) {

    $routeProvider.when("/", {
        templateUrl: "Home"
    }).when("/Exam", {
        templateUrl: "Exam"
    }).when("/MyQuestions", {
        templateUrl: "MyQuestions"
    });
});

app.controller('HomeController', ['$scope', '$http', '$location', 'DataService', function ($scope, $http, $location, DataService) {
        console.log('HomeController');
        console.log('going to laod google charts');
        google.charts.load('current', {packages: ['corechart']});
        google.charts.setOnLoadCallback(function () {

            let data = {
                chartType: 'Pie'
            };
            $http.post('myCharts', data).then(function (successResponse) {
                console.log(successResponse);

                // Define the chart to be drawn.
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Catagory');
                data.addColumn('number', 'Percentage');
                data.addRows(successResponse.data.data);

                // Instantiate and draw the chart.
                var chart = new google.visualization.PieChart($('#piChart')[0]);
                chart.draw(data, null);
            }, function (errorResponse) {
                console.log(errorResponse);
            });
        });
        google.charts.setOnLoadCallback(function () {

            let data = {
                chartType: 'Scatter'
            };
            $http.post('myCharts', data).then(function (successResponse) {
                console.log(successResponse);
                successResponse.data.data.splice(0, 0, ['Asked', 'Correctness']);
                var dataFinal = google.visualization.arrayToDataTable(successResponse.data.data);
                dataFinal.vg[0].type = 'number';
                dataFinal.vg[1].type = 'number';
                var options = {
                    title: 'Effectiveness comparison',
                    hAxis: {title: 'Asked', minValue: 0, maxValue: 100},
                    vAxis: {title: 'Correctness', minValue: 0, maxValue: 100},
                    legend: 'none'
                };

                var chart = new google.visualization.ScatterChart($('#scatterChart')[0]);

                chart.draw(dataFinal, options);
            }, function (errorResponse) {
                console.log(errorResponse);
            });
        });

    }]);
app.controller('eApp', ['$scope', '$location', function ($scope, $location) {
        console.log('eApp started');

    }]);
//Further controllers for Wizards

app.controller('addQuestionWizardController', function ($scope, $http) {
    console.log('addQuestionWizardController');
});
app.controller('ExamController', ['$scope', '$http', '$location', 'DataService', function ($scope, $http, $location, DataService) {
        console.log('ExamController');
        let currQuesSet = DataService.currentQuestionSet;
        $scope.questionNumber = 0;
        $scope.examQuestionResponse = [];
        $scope.displayLayout = {};
        $scope.displayLayout.wordMeaningLayout1 = false;
        $scope.displayLayout.pointToRememberLayout = false;
        $scope.displayLayout.wordMeaningLayout2 = false;
        $scope.type = currQuesSet[0].questionType;
        console.log(currQuesSet);
        $scope.setScreenForQuestions = function () {
            //Here I have to set the screen for the Questions asked
            if ($scope.examQuestionResponse[$scope.questionNumber])
            {
                //In this case we jsut need to set the values
                if ($scope.type == 'Word Meaning') {
                    $scope.wmResult = currQuesSet[$scope.questionNumber].results[0];
                    if ($scope.examQuestionResponse[$scope.questionNumber].response)
                    {
                        jQuery(jQuery('#wordMeaningLayout1 #layout1RadioOptionsDiv input')[0]).prop('checked', true);
                    } else {

                        jQuery(jQuery('#wordMeaningLayout1 #layout1RadioOptionsDiv input')[1]).prop('checked', true);
                    }
                    $scope.displayLayout.wordMeaningLayout1 = true;
                    $scope.displayLayout.pointToRememberLayout = false;
                    $scope.displayLayout.wordMeaningLayout2 = false;
                } else if ($scope.type == 'Point To Remember') {
                    $scope.displayLayout.wordMeaningLayout1 = false;
                    $scope.displayLayout.pointToRememberLayout = true;
                    $scope.displayLayout.wordMeaningLayout2 = false;
                    $scope.ptrResult = currQuesSet[$scope.questionNumber];
                    if ($scope.examQuestionResponse[$scope.questionNumber].response)
                    {
                        jQuery(jQuery('#pointToRememberLayout #layout1RadioOptionsDiv input')[0]).prop('checked', true);
                    } else {

                        jQuery(jQuery('#pointToRememberLayout #layout1RadioOptionsDiv input')[1]).prop('checked', true);
                    }

                }
            } else
            {
                //In this case we need to choose the layout
                //First get to know that is the question type
                if ($scope.type == 'Word Meaning') {
                    $scope.wmResult = currQuesSet[$scope.questionNumber].results[0];

                    $scope.displayLayout.wordMeaningLayout1 = true;
                    $scope.displayLayout.pointToRememberLayout = false;
                    $scope.displayLayout.wordMeaningLayout2 = false;


                } else if ($scope.type == 'Point To Remember') {
                    $scope.displayLayout.wordMeaningLayout1 = false;
                    $scope.displayLayout.pointToRememberLayout = true;
                    $scope.displayLayout.wordMeaningLayout2 = false;
                    $scope.ptrResult = currQuesSet[$scope.questionNumber];
                }
            }

        };
        $scope.previousQuestion = function ($event) {
            console.log('previousQuestion');
            if ($scope.questionNumber > 0)
            {
                $scope.questionNumber--;
                $scope.type = currQuesSet[$scope.questionNumber].questionType;
                $scope.setScreenForQuestions();
            }
        };
        $scope.nextQuestion = function ($event) {
            console.log('nextQuestion');
            if (currQuesSet.length - 1 > $scope.questionNumber)
            {
                //Now that we are moving to next question 
                //we need to check if current question is answered
                if ($scope.displayLayout.wordMeaningLayout1) {
                    //now get the radio button
                    if (!jQuery(jQuery('#wordMeaningLayout1 #layout1RadioOptionsDiv input')[0]).prop('checked') && !jQuery(jQuery('#wordMeaningLayout1 #layout1RadioOptionsDiv input')[1]).prop('checked'))
                    {
                        alert('Atleast answer it!');
                        return false;
                    } else
                    {
                        //here we need to  set the radio button to null
                        jQuery('#wordMeaningLayout1 #layout1RadioOptionsDiv input').prop('checked', false);
                    }
                } else if ($scope.displayLayout.pointToRememberLayout) {
                    if (!jQuery(jQuery('#pointToRememberLayout #layout1RadioOptionsDiv input')[0]).prop('checked') && !jQuery(jQuery('#pointToRememberLayout #layout1RadioOptionsDiv input')[1]).prop('checked'))
                    {
                        alert('Atleast answer it!');
                        return false;
                    } else
                    {
                        //here we need to set radio button to null

                        jQuery('#pointToRememberLayout #layout1RadioOptionsDiv input').prop('checked', false);
                    }
                } else if ($scope.displayLayout.wordMeaningLayout2) {
                }
                $scope.questionNumber++;
                $scope.type = currQuesSet[$scope.questionNumber].questionType;
                $scope.setScreenForQuestions();
            }
        };
        $scope.questionAnswered = function ($event) {
            $scope.examQuestionResponse[$scope.questionNumber] = {
                response: jQuery($event.currentTarget.children[0]).prop('checked'),
                questionID: currQuesSet[$scope.questionNumber].id
            };
        };
        $scope.handleSubmit = function ($event) {
            console.log('handleSubmit');
            var succ = [];
            var fail = [];
            for (let response = 0; response < $scope.examQuestionResponse.length; response++) {
                var respObj = $scope.examQuestionResponse[response];
                if (respObj.response) {
                    succ.push(respObj.questionID);
                } else {

                    fail.push(respObj.questionID);
                }
            }
            let data = {
                result: $scope.examQuestionResponse,
                positive: succ,
                negative: fail
            };
            $http.post('examResult', data).then(function (successResponse) {
                console.log(successResponse);
                $location.path('/');
            }, function (errorResponse) {
                console.log(errorResponse);
            });
        };
        $scope.setScreenForQuestions();

    }]);
app.controller('addPointWizardController', ['$scope', '$http', function ($scope, $http) {
        console.log('addPointWizardController');
        $scope.handleSubmit = function ($event) {
            if (jQuery($event.currentTarget.parentElement.previousElementSibling.children).find('.d-block')[0].dataset.action == 'submit')
            {
                var data = $scope.fields;
                $http.post('addPointToRemember', data).then(function (successResponse) {
                    console.log(successResponse);
                    jQuery('#addPointWizard').modal('hide');
                }, function (errorResponse) {
                    console.log(errorResponse);
                });
            }
        };
    }]);
app.controller('addWordMeaningController', function ($scope, $http) {
    console.log('addWordMeaningController');
    $scope.wmResult = {};
    $scope.wMeaning = {};
    $scope.handleSubmit = function ($event) {
        if (jQuery($event.currentTarget.parentElement.previousElementSibling).find('.d-block')[0].dataset.action === 'submit')
        {
            var data = $scope.wMeaning;
            $http.post('addWordMeaning', data).then(function (successResponse) {
                console.log(successResponse);
                jQuery('#addWordMeaningWizard').modal('hide');
            }, function (errorResponse) {
                console.log(errorResponse);
            });
        }
    };
    $scope.handleSearch = function ($event) {

        console.log($event.currentTarget.previousElementSibling);
        $scope.wmResult = {};
        $http.get('searchWord/' + $event.currentTarget.previousElementSibling.value, {
            url: 'searchWord/' + $event.currentTarget.previousElementSibling.value,
            method: "GET",
            params: {
                define: $event.currentTarget.previousElementSibling.value,
                lang: "en"
            }
        }).then(function (successResponse) {
            console.log(successResponse);
            $scope.wMeaning = successResponse.data;
            $scope.wmResult = successResponse.data.results[0];
        }, function (errorResponse) {
            console.log(errorResponse);
        });

    };
});
app.controller('revisionInputModal', ['$scope', '$http', '$location', 'DataService', function ($scope, $http, $location, DataService) {
        console.log('revisionInputModal');
        $scope.handleSubmit = function ($event) {
            if (jQuery($event.currentTarget.parentElement.previousElementSibling).find('.d-block')[0].dataset.action === 'submit')
            {
//            var data = $scope.wMeaning;
                var data = {};
                data.examType = $scope.currentType;
                data.fields = $scope.fields[data.examType.href];
                console.log(data.fields);
                $http.post('revisionQuestionPaper', data).then(function (successResponse) {
                    console.log(successResponse);
                    DataService.currentQuestionSet = successResponse.data.questionList;
                    jQuery('#revisionInputModal').modal('hide');
                    $location.path('/Exam');
                }, function (errorResponse) {
                    console.log(errorResponse);
                });
            }
        };
        $scope.questionTypes = [{value: 'Random', href: 'RANDOM'},
            {value: 'Word Meaning', href: 'WORD_MEANING'},
            {value: 'Point To Remember', href: 'POINT_TO_REMEMBER'}];
        $scope.currentType = $scope.questionTypes[0];
        $scope.quesTypeClick = function ($event) {
            console.log('quesTypeClick');
            $scope.currentType = $scope.questionTypes.find(x => x.href === $event.currentTarget.dataset.href);
            return false;
        };
    }]);
app.controller('myQuestionsController', ['$scope', '$http', '$location', 'DataService', function ($scope, $http, $location, DataService) {
        console.log('myQuestionsController');

        $http.get('myQuestions').then(function (successResponse) {
            console.log(successResponse);
            $scope.wordMeanings = successResponse.data.wordMeanings;
            $scope.pointToRemembers = successResponse.data.pointToRemember;
        }, function (errorResponse) {
            console.log(errorResponse);
        });
        $scope.wmResult = '';
        $scope.handleSubmit = function ($event) {
            console.log('handleSubmit myQuestionController');
        };
        $scope.tabChange = function ($event) {
            console.log('handled tab');
            return false;
        };
        $scope.trashQuestion = function ($event) {
            console.log('handled tab');
            $http.delete('trashQuestion', {id: 'this is data'}).then(function (e) {
                console.log('Success: ' + e);
            }, function (e) {
                console.log('error: ' + e);
            });
            return false;
        };
        $scope.viewWord = function ($event) {
            console.log('handled tab');

            console.log(this.wmResult);
            wmResult = $.parseJSON(this.wordMeanings[$event.target.dataset.windex].oxfordResponse).results[0];

        };

    }]);
//Some common Event Handling
$(document).ready(function () {

    $('.modal').on('show.bs.modal', function () {
        //Every time modal reset code here
        console.log('This');
        let ds = $(this).find('.d-block')[0].dataset;

        $(this).find('.previousButton').text(ds.previoustext);
        $(this).find('.nextButton').text(ds.nexttext);
    });
    $('.modal .previousButton').on('click', function (e) {
        let tabid = e.target.dataset.tabid;
        if (tabid >= e.target.parentElement.previousElementSibling.dataset.tabidmin)
        {
            $(e.target.parentElement.previousElementSibling.children).find('.form-group').addClass('d-none').removeClass('d-block');
            ;
            $(e.target.parentElement.previousElementSibling.children).find('.form-group').filter(function () {
                return $(this).data('tabid') == tabid;
            }).addClass('d-block').removeClass('d-none');
            tabid--;
            e.target.dataset.tabid = tabid;
            e.target.nextElementSibling.dataset.tabid = tabid + 2;

            let ds = $(e.target.parentElement.previousElementSibling.children).find('div').filter('.d-block')[0].dataset;

            $(e.target.parentElement).find('.previousButton').text(ds.previoustext);
            $(e.target.parentElement).find('.nextButton').text(ds.nexttext);
        }
    });

    $('.modal .nextButton').on('click', function (e) {
        let tabid = e.target.dataset.tabid;
        if (tabid <= e.target.parentElement.previousElementSibling.dataset.tabidmax)
        {
            //Now validate the fields present on the part of the form
            var tab = $(e.target.parentElement.previousElementSibling.children).find('.d-block');
            var valid = true;
            $('input', tab).each(function (i, v) {
                valid = validator.element(v) && valid;
            });

            if (!valid) {
                return;
            }

            $(e.target.parentElement.previousElementSibling.children).find('.form-group').addClass('d-none').removeClass('d-block');
            $(e.target.parentElement.previousElementSibling.children).find('.form-group').filter(function () {
                return $(this).data('tabid') == tabid;
            }).addClass('d-block').removeClass('d-none');

            tabid++;
            e.target.dataset.tabid = tabid;
            e.target.previousElementSibling.dataset.tabid = tabid - 2;

            let ds = $(e.target.parentElement.previousElementSibling.children).find('div').filter('.d-block')[0].dataset;

            $(e.target.parentElement).find('.previousButton').text(ds.previoustext);
            $(e.target.parentElement).find('.nextButton').text(ds.nexttext);
        } else if ($(e.target.parentElement.previousElementSibling).find('.d-block')[0].dataset.action == "submit")
        {
            console.log('left for angular');
        }

    });

    var validator = $('#addPointWizardForm').validate({
        rules: {
            text1: {
                required: true
            },
            text2: {

                required: true
            },
            text3: {
                required: true
            },
            text4: {
                required: true
            },
            text5: {
                required: true
            }
        }
    });
});